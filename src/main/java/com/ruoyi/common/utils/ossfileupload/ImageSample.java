package com.ruoyi.common.utils.ossfileupload;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ListBucketsRequest;
import com.aliyun.oss.model.PutObjectRequest;

/**
 * 断点续传下载用法示例
 *
 */
public class ImageSample {

    private static String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
    private static String accessKeyId = "LTAIBuCMwxx9ETDE";
    private static String accessKeySecret = "JUPts8LOFUgQ0bHVeU6penKGop02T2";
    private static String bucketName = "sgyzxjy";
    private static String filePath = "http://sgyzxjy.oss-cn-hangzhou.aliyuncs.com";
    private static String key = "upload/2017/03/13/16c0bcba-8b31-4263-a82f-c7a62af11728.jpg";
    
    /**
     * 上传图片到oss存储
     * @param file 文件
     * @param key 路径+图片名称
     * @return
     */
    public static String imageUpload(File file,String key){
    	OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    	String filePathName  = filePath+"/"+key;
    	try {
			/*
			 * Determine whether the bucket exists
			 */
			if (!ossClient.doesBucketExist(bucketName)) {
			    /*
			     * Create a new OSS bucket
			     */
			    ossClient.createBucket(bucketName);
			    CreateBucketRequest createBucketRequest= new CreateBucketRequest(bucketName);
			    createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
			    ossClient.createBucket(createBucketRequest);
			}

			/*
			 * List the buckets in your account
			 */
			
			ListBucketsRequest listBucketsRequest = new ListBucketsRequest();
			listBucketsRequest.setMaxKeys(500);
			
			for (Bucket bucket : ossClient.listBuckets()) {
			}
			
			/*
			 * Upload an object to your bucket
			 */
			ossClient.putObject(new PutObjectRequest(bucketName, key, file));
		} catch (OSSException oe) {
			oe.printStackTrace();
        } catch (ClientException ce) {
        	ce.printStackTrace();
        } catch(Exception e){
        	e.printStackTrace();
        }finally {
            ossClient.shutdown();
        }
        
    	return filePathName;
    }
    
    public static String imageByteUpload(MultipartFile file,String key){
    	String filePathName  = filePath+"/"+key;
    	OSS client = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
        	
			client.putObject(bucketName, key, new ByteArrayInputStream(file.getBytes()));
		
		} catch (IOException e) {
			e.printStackTrace();
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
            client.shutdown();
        }
    	return filePathName;
    }
    
    public static String byteUpload(byte[] fileBytes,String key){
    	String filePathName  = filePath+"/"+key;
    	OSS client = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
        	
			client.putObject(bucketName, key, new ByteArrayInputStream(fileBytes));
		
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
            client.shutdown();
        }
    	return filePathName;
    }
    
    
    
    /**
     * 上传视频到oss存储
     * @param file 文件
     * @param key 路径+图片名称
     * @return
     */
    public static String videoUpload(File file,String key){
    	OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    	String filePathName  = filePath+"/"+key;
    	try {
			/*
			 * Determine whether the bucket exists
			 */
			if (!ossClient.doesBucketExist(bucketName)) {
			    /*
			     * Create a new OSS bucket
			     */
			    ossClient.createBucket(bucketName);
			    CreateBucketRequest createBucketRequest= new CreateBucketRequest(bucketName);
			    createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
			    ossClient.createBucket(createBucketRequest);
			}

			/*
			 * List the buckets in your account
			 */
			
			ListBucketsRequest listBucketsRequest = new ListBucketsRequest();
			listBucketsRequest.setMaxKeys(500);
			
			for (Bucket bucket : ossClient.listBuckets()) {
			}
			
			/*
			 * Upload an object to your bucket
			 */
			ossClient.putObject(new PutObjectRequest(bucketName, key, file));
		} catch (OSSException oe) {
			oe.printStackTrace();
        } catch (ClientException ce) {
        	ce.printStackTrace();
        } catch(Exception e){
        	e.printStackTrace();
        }finally {
            ossClient.shutdown();
        }
        
    	return filePathName;
    }
    
    /**
     * 上传压缩图片到oss存储
     * @param file 文件
     * @param key 路径+图片名称
     * @return
     */
    public static String imageZoom(File file,String key,int x,int y){
    	OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//    	endpoint = "http://img-cn-beijing.aliyuncs.com";
    	String filePathName  = filePath + "/" + key;
    	try {
    		/*
    		 * Determine whether the bucket exists
    		 */
    		if (!ossClient.doesBucketExist(bucketName)) {
    			/*
    			 * Create a new OSS bucket
    			 */
    			ossClient.createBucket(bucketName);
    			CreateBucketRequest createBucketRequest= new CreateBucketRequest(bucketName);
    			createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
    			ossClient.createBucket(createBucketRequest);
    		}
    		
    		/*
    		 * List the buckets in your account
    		 */
    		
    		ListBucketsRequest listBucketsRequest = new ListBucketsRequest();
    		listBucketsRequest.setMaxKeys(500);
    		
//    		ImageSample.imageUpload(file, key);
    		ossClient.putObject(new PutObjectRequest(bucketName, key, file));

    		String style = "image/resize,m_fixed,w_"+x+",h_"+y;  
    		// 缩放
            GetObjectRequest request = new GetObjectRequest(bucketName, key);
            request.setProcess(style);
            ossClient.getObject(request, new File("f75dad89-5ce9-43f9-bb3f-4532dacd022b.jpg"));
//            String urlStr = filePathName+"?x-oss-process="+style;
//            logger.debug(urlStr);
//            HttpTool.get(urlStr);
    		
//    		// 过期时间10分钟
//    	    Date expiration = new Date(new Date().getTime() + 1000 * 60 * 10 );
//    	    GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, key, HttpMethod.GET);
//    	    req.setExpiration(expiration);
//    	    req.setProcess(style);
//    	    URL signedUrl = ossClient.generatePresignedUrl(req);
//    	    logger.debug(signedUrl);
    	} catch (OSSException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (ClientException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	return filePathName;
    }
    
    /**
     * 裁剪图片到oss存储
     * @param file 文件
     * @param key 路径+图片名称
     * @return
     */
    public static String imageCut(File file,String key,int x,int y,int w,int h){
    	OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    	String filePathName  = filePath+"/"+key;
    	try {
    		/*
    		 * Determine whether the bucket exists
    		 */
    		if (!ossClient.doesBucketExist(bucketName)) {
    			/*
    			 * Create a new OSS bucket
    			 */
    			ossClient.createBucket(bucketName);
    			CreateBucketRequest createBucketRequest= new CreateBucketRequest(bucketName);
    			createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
    			ossClient.createBucket(createBucketRequest);
    		}
    		
    		/*
    		 * List the buckets in your account
    		 */
    		
    		ListBucketsRequest listBucketsRequest = new ListBucketsRequest();
    		listBucketsRequest.setMaxKeys(500);
    		
    		String filePath = ImageSample.imageUpload(file, key);
    		
    		// 裁剪
    		String style = "image/crop,w_100,h_100,x_100,y_100,r_1"; 
    		GetObjectRequest request = new GetObjectRequest(bucketName, key);
    		request.setProcess(style);
    		
    		
    		ossClient.getObject(request, file);
    	} catch (OSSException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (ClientException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	return filePathName;
    }
    
    public static void main(String[] args) throws IOException {        

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        
        try {
            // 缩放
            //String style = "image/resize,m_fixed,w_100,h_100";  
            //GetObjectRequest request = new GetObjectRequest(bucketName, key);
            //request.setProcess(style);
            
            //ossClient.getObject(request, new File("upload/2017/03/13/16c0bcba-8b31-4263-a82f-c7a62af11728.jpg"));
            
        	
        	
        	// 上传文件
        	ossClient.putObject(bucketName, key, new File("C:/Users/shaochangchao/Desktop/bmdh.png"));
        	// 关闭client
        	ossClient.shutdown();
        	
        	
        	
        	
        	
//            // 裁剪
//            style = "image/crop,w_100,h_100,x_100,y_100,r_1"; 
//            request = new GetObjectRequest(bucketName, key);
//            request.setProcess(style);
//            
//            ossClient.getObject(request, new File("example-crop.jpg"));
//            
//            // 旋转
//            style = "image/rotate,90"; 
//            request = new GetObjectRequest(bucketName, key);
//            request.setProcess(style);
//            
//            ossClient.getObject(request, new File("example-rotate.jpg"));
//            
//            // 锐化
//            style = "image/sharpen,100"; 
//            request = new GetObjectRequest(bucketName, key);
//            request.setProcess(style);
//            
//            ossClient.getObject(request, new File("example-sharpen.jpg"));
//            
//            // 水印
//            style = "image/watermark,text_SGVsbG8g5Zu-54mH5pyN5YqhIQ"; 
//            request = new GetObjectRequest(bucketName, key);
//            request.setProcess(style);
//            
//            ossClient.getObject(request, new File("example-watermark.jpg"));
//            
//            // 格式转换
//            style = "image/format,png"; 
//            request = new GetObjectRequest(bucketName, key);
//            request.setProcess(style);
//            
//            ossClient.getObject(request, new File("example-format.png"));
//            
//            // 图片信息
//            style = "image/info"; 
//            request = new GetObjectRequest(bucketName, key);
//            request.setProcess(style);
//            
//            ossClient.getObject(request, new File("example-info.txt"));
            
        } catch (OSSException oe) {
        } catch (ClientException ce) {
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
    }
}
