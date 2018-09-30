package com.bonc.common.utils;


import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.GridFSUploadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
/**
 * http://mongodb.github.io/mongo-java-driver/3.6/driver-async/tutorials/gridfs/
 * MongoDb 存儲文件
 * @author j
 *
 */
public class MongoGridFSClient {
	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document> collection ;
	public MongoGridFSClient(String iMAGE_SERVER_URL) {
		// 获取连接
		MongoClientURI connectionString = new MongoClientURI("mongodb://"+iMAGE_SERVER_URL+":27017");
		mongoClient= new MongoClient(connectionString);
		database = mongoClient.getDatabase("mydb");
	}
	/**
	 * 上传文件到mongoDB
	 * @param bytes
	 * @param extName
	 * @return
	 */
	public String uploadFile(byte[] bytes, String extName ,String contentType) {
		GridFSUploadStream uploadStream = null;
		try {
			GridFSBucket gridFSFilesBucket = GridFSBuckets.create(database, "files");

			GridFSUploadOptions options = new GridFSUploadOptions()
					.chunkSizeBytes(358400)
					.metadata(new Document("type", extName).append("contentType", contentType)); 
		
			uploadStream = gridFSFilesBucket.openUploadStream(CommonUtils.getUUID(), options);
			uploadStream.write(bytes);
			return uploadStream.getObjectId().toHexString();
		} finally {
			if(null!=uploadStream)
				uploadStream.close();
		}


	}

	/**
	 *  下載文件
	 * @param fileId
	 * @return
	 */
	public  byte[] downLoad(String fileId) {
		GridFSBucket gridFSFilesBucket = GridFSBuckets.create(database, "files");
		ObjectId objectId=new ObjectId(fileId); //The id of a file uploaded to GridFS, initialize to valid file id 

		GridFSDownloadStream downloadStream = gridFSFilesBucket.openDownloadStream(objectId);
		GridFSFile gridFSFile = downloadStream.getGridFSFile();
		int fileLength = (int) gridFSFile.getLength();
		byte[] bytesToWriteTo = new byte[fileLength];
		downloadStream.read(bytesToWriteTo);
		downloadStream.close();
		return bytesToWriteTo;

	}




}
