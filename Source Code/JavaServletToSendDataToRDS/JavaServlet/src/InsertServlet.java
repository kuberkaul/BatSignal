
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.util.json.JSONArray;
import com.amazonaws.util.json.JSONObject;
import com.columbia.db.RDSManager;

/**
 * Servlet implementation class RateServlet
 */

public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static RDSManager rdsManager = new RDSManager();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String print = "";
		try
		{
	
			BufferedReader reader = request.getReader();
		    StringBuilder sb = new StringBuilder();
		    String line = reader.readLine();
		    while (line != null) {
		        sb.append(line + "\n");
		        line = reader.readLine();
		    }
		    reader.close();
		    String data = sb.toString();
		    JSONObject json;
			json = new JSONObject(data);
			JSONArray jsArray = json.getJSONArray("jsArray");
			char c = jsArray.get(0).toString().charAt(0);
			//linacc-0,gyro-1,photo-2,rotvec-3,baro-4,mic-5,gps-6,bat-7,prox-8
			
			switch(c)
			{
				case 'q':	{
					System.out.println("user");
					String cn = jsArray.get(1).toString();
					String name = jsArray.get(2).toString();
					String linacc = jsArray.get(3).toString();
					String gyro = jsArray.get(4).toString();
					String photo = jsArray.get(5).toString();
					String rotvec = jsArray.get(6).toString();
					String baro = jsArray.get(7).toString();
					String mic = jsArray.get(8).toString();
					String gps = jsArray.get(9).toString();
					String bat = jsArray.get(10).toString();
					String prox = jsArray.get(11).toString();
					print += rdsManager.setUsersData(cn, name, bat, linacc, gyro, prox, baro, mic, photo, rotvec, gps);
				}
				break;
				case '0':	{
					System.out.println("0");
					String cn = jsArray.get(1).toString();
					String t = jsArray.get(2).toString();
					String x = jsArray.get(3).toString();
					String y = jsArray.get(4).toString();
					String z = jsArray.get(5).toString();
					rdsManager.setLinearAccelerometerData(cn, t, x, y, z);
				}
				break;
				case '1':	{
					System.out.println("1");
					String cn = jsArray.get(1).toString();
					String t = jsArray.get(2).toString();
					String x = jsArray.get(3).toString();
					String y = jsArray.get(4).toString();
					String z = jsArray.get(5).toString();
					rdsManager.setGyroscopeData(cn, t, x, y, z);
				}
				break;
				case '2':	{
					System.out.println("2");
					String cn = jsArray.get(1).toString();
					String t = jsArray.get(2).toString();
					String l = jsArray.get(3).toString();
					rdsManager.setLightData(cn, t, l);	
				}
				break;
				case '3':	{
					System.out.println("3");
					String cn = jsArray.get(1).toString();
					String t = jsArray.get(2).toString();
					String x = jsArray.get(3).toString();
					String y = jsArray.get(4).toString();
					String z = jsArray.get(5).toString();
					rdsManager.setRotationalVectorData(cn, t, x, y, z);	
				}
				break;
				case '4':	{
					System.out.println("4");
					String cn = jsArray.get(1).toString();
					String t = jsArray.get(2).toString();
					String pressure = jsArray.get(3).toString();
					rdsManager.setPressureData(cn, t, pressure);	
				}
				break;
//				case '5':	{
//				System.out.println("5");
//					String cn = jsArray.get(1).toString();
//					String t = jsArray.get(2).toString();
//					String l = jsArray.get(3).toString();
//					rdsManager.setBatteryData(cn, t, l);	
//				}
//				break;
				case '6':	{
				System.out.println("6");
					String cn = jsArray.get(1).toString();
					String t = jsArray.get(2).toString();
					String x = jsArray.get(3).toString();
					String y = jsArray.get(4).toString();
					System.out.println("GPS--> " + x + " " + y);
					rdsManager.setGpsData(cn, t, x, y);
				}
				break;
				case '7':	{
					System.out.println("7");
					String cn = jsArray.get(1).toString();
					String t = jsArray.get(2).toString();
					String l = jsArray.get(3).toString();
					rdsManager.setBatteryData(cn, t, l);	
				}
				break;
				case '8':	{
					System.out.println("8");
					String cn = jsArray.get(1).toString();
					String t = jsArray.get(2).toString();
					String prox = jsArray.get(3).toString();
					rdsManager.setProximityData(cn, t, prox);	
				}
				break;
			}
			
//			int cellnumber = -1;
//			int level = -1;
//			int time = -1;
//			Collection<Part> parts = request.getParts();
//			if(parts == null)
//			{
//				print += "getParts() returned null\n";
//			}
//			else if(parts.size() == 0)
//			{
//				print += "parts size is 0";
//			}
//			Part p = request.getPart("cellnumber");
//			BufferedReader reader = new BufferedReader( new InputStreamReader(p.getInputStream()));
//			String line ="";
//			while((line=reader.readLine())!=null)
//			{
//			    // do Something with a line
//	//			String cellnumberS = request.getParameter("cellnumber");
//				cellnumber = Integer.parseInt(line);
//				
//			}
//			
//			p = request.getPart("time");
//			BufferedReader reader2 = new BufferedReader( new InputStreamReader(p.getInputStream()));
//			String line2 ="";
//			while((line2=reader2.readLine())!=null)
//			{
//			    // do Something with a line
//	//			String cellnumberS = request.getParameter("cellnumber");
//				time = Integer.parseInt(line2);
//				
//			}
//			p = request.getPart("level");
//			BufferedReader reader3 = new BufferedReader( new InputStreamReader(p.getInputStream()));
//			String line3 ="";
//			while((line3=reader3.readLine())!=null)
//			{
//			    // do Something with a line
//	//			String cellnumberS = request.getParameter("cellnumber");
//				level = Integer.parseInt(line3);
//				
//			}
//	//		String cellnumberS = request.getParameter("level");
//	//		cellnumber = Integer.parseInt(cellnumberS);
//	//		String levelS = request.getParameter("level");
//	//		level = Integer.parseInt(levelS);
//	//		String timeS = request.getParameter("time");
//	//		time = Integer.parseInt(timeS);
//			
//			System.out.println("YOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\n" + cellnumber + " " + level + " " + time);
//			rdsManager = new RDSManager();
//			rdsManager.setBatteryData(cellnumber, time, level);
			response.setHeader("error", "no errorsssss------\n" + print + c + "<<<<<<<<<<");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			String err = print + "============\n" + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace());
			response.setHeader("error", err);
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//		rdsManager = new RDSManager();
//		String levelS = request.getParameter("level");
//		String cellnumberS = request.getParameter("cellnumber");
//		String timeS = request.getParameter("time");
//		int level = Integer.parseInt(levelS);
//		int cellnumber = Integer.parseInt(cellnumberS);
//		int time = Integer.parseInt(timeS);
//		rdsManager.setBatteryData(cellnumber, time, level);
	}
}
