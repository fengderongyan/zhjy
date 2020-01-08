package com.ruoyi.common.utils.ossfileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CompleteMultipartUploadRequest;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.InitiateMultipartUploadRequest;
import com.aliyun.oss.model.InitiateMultipartUploadResult;
import com.aliyun.oss.model.ListPartsRequest;
import com.aliyun.oss.model.PartETag;
import com.aliyun.oss.model.PartListing;
import com.aliyun.oss.model.PartSummary;
import com.aliyun.oss.model.UploadPartRequest;
import com.aliyun.oss.model.UploadPartResult;

/**
 * This sample demonstrates how to upload multiparts to Aliyun OSS 
 * using the OSS SDK for Java.
 */
public class MultipartUploadSample {
    

	private static String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
    private static String accessKeyId = "LTAIBuCMwxx9ETDE";
    private static String accessKeySecret = "JUPts8LOFUgQ0bHVeU6penKGop02T2";
    private static String bucketName = "sgyzxjy";
    private static String filePath = "http://sgyzxjy.oss-cn-hangzhou.aliyuncs.com";
	
    private static OSSClient client = null;
    
    private static String localFilePath = "*** Provide local file path ***";
    
    
    
    
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);
    private static List<PartETag> partETags = Collections.synchronizedList(new ArrayList<PartETag>());
    
    
    
    
    /**
     * 上传图片到oss存储
     * @param file 文件
     * @param key 路径+图片名称
     * @return
     */
    public static String multipartImageUpload(MultipartFile file,String key){
    	
    	ClientConfiguration conf = new ClientConfiguration();
        conf.setIdleConnectionTime(1000);
        client = new OSSClient(endpoint, accessKeyId, accessKeySecret, conf);
    	String filePathName  = filePath+"/"+key;
    	try {
    		String uploadId = claimUploadId( key);
    		
    		final long partSize = 5 * 1024 * 1024L;   // 5MB
    		
    		
    		CommonsMultipartFile cf= (CommonsMultipartFile)file; 
            DiskFileItem diskFileItem = (DiskFileItem)cf.getFileItem(); 
            File sampleFile = diskFileItem.getStoreLocation();
    		
            long fileLength = sampleFile.length();
            int partCount = (int) (fileLength / partSize);
            if (fileLength % partSize != 0) {
                partCount++;
            }
            if (partCount > 10000) {
                throw new RuntimeException("Total parts count should not exceed 10000");
            } else {                
                System.out.println("Total parts count " + partCount + "\n");
            }
            
            /*
             * Upload multiparts to your bucket
             */
            System.out.println("Begin to upload multiparts to OSS from a file\n");
            for (int i = 0; i < partCount; i++) {
                long startPos = i * partSize;
                long curPartSize = (i + 1 == partCount) ? (fileLength - startPos) : partSize;
                executorService.execute(new PartUploader(sampleFile, startPos, curPartSize, i + 1, uploadId, key));
            }
            
            /*
             * Waiting for all parts finished
             */
            executorService.shutdown();
            while (!executorService.isTerminated()) {
                try {
                    executorService.awaitTermination(5, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            /*
             * Verify whether all parts are finished
             */
            if (partETags.size() != partCount) {
                throw new IllegalStateException("Upload multiparts fail due to some parts are not finished yet");
            } else {
                System.out.println("Succeed to complete multiparts into an object named " + key + "\n");
            }
            
            /*
             * View all parts uploaded recently
             */
            //listAllParts(uploadId);
            
            /*
             * Complete to upload multiparts
             */
            completeMultipartUpload(uploadId, key);
            
            /*
             * Fetch the object that newly created at the step below.
             */
            System.out.println("Fetching an object");
            client.getObject(new GetObjectRequest(bucketName, key), new File(localFilePath));
            
    		
		} catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } finally {
            /*
             * Do not forget to shut down the client finally to release all allocated resources.
             */
            if (client != null) {
                client.shutdown();
            }
        }
        
    	return filePathName;
    }
    
    
    /**
     * 描述：视频分片上传
     * @param file
     * @param key
     * @return
     * @author zhangcc
     * @Date : 2019-05-09
     */
    public static String multipartVidesUpload(MultipartFile file,String key1){
    	ClientConfiguration conf = new ClientConfiguration();
    	// 设置OSSClient允许打开的最大HTTP连接数，默认为1024个。
    	conf.setMaxConnections(2048);
    	// 设置Socket层传输数据的超时时间，默认为50000毫秒。
    	conf.setSocketTimeout(10000);
    	// 设置建立连接的超时时间，默认为50000毫秒。
    	conf.setConnectionTimeout(10000);
    	// 设置从连接池中获取连接的超时时间（单位：毫秒），默认不超时。
    	conf.setConnectionRequestTimeout(1000);
    	// 设置连接空闲超时时间。超时则关闭连接，默认为60000毫秒。
    	conf.setIdleConnectionTime(20000);
    	// 设置失败请求重试次数，默认为3次。
    	conf.setMaxErrorRetry(5);
        client = new OSSClient(endpoint, accessKeyId, accessKeySecret, conf);
    	String filePathName  = filePath+"/"+key1;
    	try {
    		InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(bucketName, key1);
    		InitiateMultipartUploadResult result = client.initiateMultipartUpload(request);
    		// 返回uploadId，它是分片上传事件的唯一标识，您可以根据这个ID来发起相关的操作，如取消分片上传、查询分片上传等。
    		String uploadId = result.getUploadId();
    		
    		final long partSize = 1 * 1024 * 1024L;   // 1MB
    		
    		
    		CommonsMultipartFile cf= (CommonsMultipartFile)file; 
            DiskFileItem diskFileItem = (DiskFileItem)cf.getFileItem(); 
            File sampleFile = diskFileItem.getStoreLocation();
    		
            long fileLength = sampleFile.length();
            int partCount = (int) (fileLength / partSize);
            if (fileLength % partSize != 0) {
                partCount++;
            }
            if (partCount > 10000) {
                throw new RuntimeException("Total parts count should not exceed 10000");
            } else {                
                System.out.println("Total parts count " + partCount + "\n");
            }
            
            /*
             * Upload multiparts to your bucket
             */
            System.out.println("Begin to upload multiparts to OSS from a file\n");
            for (int i = 0; i < partCount; i++) {
                long startPos = i * partSize;
                long curPartSize = (i + 1 == partCount) ? (fileLength - startPos) : partSize;
                executorService.execute(new PartUploader(sampleFile, startPos, curPartSize, i + 1, uploadId, key1));
            }
            
            /*
             * Waiting for all parts finished
             */
            executorService.shutdown();
            while (!executorService.isTerminated()) {
                try {
                    executorService.awaitTermination(5, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            /*
             * Verify whether all parts are finished
             */
            if (partETags.size() != partCount) {
                throw new IllegalStateException("Upload multiparts fail due to some parts are not finished yet");
            } else {
                System.out.println("Succeed to complete multiparts into an object named " + key1 + "\n");
            }
            
            /*
             * View all parts uploaded recently
             */
            //listAllParts(uploadId);
            
            /*
             * Complete to upload multiparts
             */
            completeMultipartUpload(uploadId, key1);
            
            /*
             * Fetch the object that newly created at the step below.
             */
            System.out.println("Fetching an object");
            client.getObject(new GetObjectRequest(bucketName, key1), new File(""));
            
    		
		} catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } finally {
            /*
             * Do not forget to shut down the client finally to release all allocated resources.
             */
            if (client != null) {
                client.shutdown();
            }
        }
        
    	return filePathName;
    }
    
    private static class PartUploader implements Runnable {
        
        private File localFile;
        private long startPos;        
        
        private long partSize;
        private int partNumber;
        private String uploadId;
        private String key;
        
        public PartUploader(File localFile, long startPos, long partSize, int partNumber, String uploadId, String key) {
            this.localFile = localFile;
            this.startPos = startPos;
            this.partSize = partSize;
            this.partNumber = partNumber;
            this.uploadId = uploadId;
            this.key = key;
        }
        
        @Override
        public void run() {
            InputStream instream = null;
            try {
                instream = new FileInputStream(this.localFile);
                instream.skip(this.startPos);
                
                UploadPartRequest uploadPartRequest = new UploadPartRequest();
                uploadPartRequest.setBucketName(bucketName);
                uploadPartRequest.setKey(key);
                uploadPartRequest.setUploadId(this.uploadId);
                uploadPartRequest.setInputStream(instream);
                uploadPartRequest.setPartSize(this.partSize);
                uploadPartRequest.setPartNumber(this.partNumber);
                
                UploadPartResult uploadPartResult = client.uploadPart(uploadPartRequest);
                System.out.println("Part#" + this.partNumber + " done\n");
                synchronized (partETags) {
                    partETags.add(uploadPartResult.getPartETag());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (instream != null) {
                    try {
                        instream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } 
    }
    
    private static File createSampleFile() throws IOException {
        File file = File.createTempFile("oss-java-sdk-", ".txt");
        file.deleteOnExit();

        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        for (int i = 0; i < 1000000; i++) {
            writer.write("abcdefghijklmnopqrstuvwxyz\n");
            writer.write("0123456789011234567890\n");
        }
        writer.close();

        return file;
    }
    
    private static String claimUploadId(String key) {
        InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(bucketName, key);
        InitiateMultipartUploadResult result = client.initiateMultipartUpload(request);
        return result.getUploadId();
    }
    
    private static void completeMultipartUpload(String uploadId, String key) {
        // Make part numbers in ascending order
        Collections.sort(partETags, new Comparator<PartETag>() {

            @Override
            public int compare(PartETag p1, PartETag p2) {
                return p1.getPartNumber() - p2.getPartNumber();
            }
        });
        
        System.out.println("Completing to upload multiparts\n");
        CompleteMultipartUploadRequest completeMultipartUploadRequest = 
                new CompleteMultipartUploadRequest(bucketName, key, uploadId, partETags);
        client.completeMultipartUpload(completeMultipartUploadRequest);
    }
    
}
