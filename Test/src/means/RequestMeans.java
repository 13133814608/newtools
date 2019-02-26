package means;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import requestmeans.GetAndPost;

public class RequestMeans {
	//判斷請求的方法是get還是post，還是rpc請求
    public String RequestsMean(String means) throws ClientProtocolException, IOException {
    	
		switch (means) {
		case "get":
			  GetAndPost.RunByGET(means);
			break;
		case "post":
			  GetAndPost.sendPost(means, means);
		default:
			  GetAndPost.RequestRpc();
			break;
		}
    	return null;
    	
    }
    
    public boolean RunorNot(String status) {
    	switch (status) {
		case "運行":
			System.out.println("可執行的數據");
			break;
		case "不可運行":
			System.out.println("不可執行的數據");
		default:
			break;
		}
		return false;
    	
    }
}
