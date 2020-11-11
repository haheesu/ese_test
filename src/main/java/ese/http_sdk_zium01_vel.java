package ese;

import java.util.Random;

import com.eseict.iot.api.ApiHandler;
import com.eseict.iot.common.SdkHeader;
import com.eseict.iot.common.config.CommonCode;
import com.eseict.iot.domain.object.ResponsePrimitive;
import com.eseict.iot.domain.resource.dataType.resourceType.ContentInstance;

public class http_sdk_zium01_vel {

	//클라이언트 모드 + http로 sdk설정, create api 호출

	private static String HOST_IP = "210.97.42.250";
	private static int HOST_PORT = 11710;
	private static String FROM = "SiotTestAE";
	private static String ENDPOINT_URI = "/iotCore/AEe5de5a59-0581-44cf-89af-fef661f981de";
	private static String STREAM = "win";
	private static String SENSOR_DATA;

	public static void main(String[] args) {
		
		SdkHeader header = new SdkHeader();
		
		header.setMode(CommonCode.SDK_MODE.CLIENT);
		header.setProtocol(CommonCode.PROTOCOL.HTTP);
		header.setHost(HOST_IP);
		header.setPort(HOST_PORT);
		
		ApiHandler apiHandler = new ApiHandler();
		apiHandler.init(header);
		
		while (true) {
			
			Random ran = new Random();
			double sensor_data = (ran.nextGaussian() + 5);
			SENSOR_DATA = Double.toString(sensor_data);
			
			
			ContentInstance contentInstance = new ContentInstance();
			contentInstance.setContent(SENSOR_DATA);
			
			ResponsePrimitive response = apiHandler.create(contentInstance, ENDPOINT_URI, STREAM, FROM);
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		// Show response here
	}
}
