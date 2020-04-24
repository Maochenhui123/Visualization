package pers.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Region;
import pers.pre.change.LIST;
import pers.util.association.Association;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long startTime = System.currentTimeMillis();
		
		/**receiving the incoming data from interface**/
		String[] attribute = request.getParameterValues("attribute");
		
		String main = request.getParameter("main");
		String region = request.getParameter("province");
		boolean flag = true;
		
		if(attribute != null) {
			for(int i=0;i<attribute.length;i++) {
				if(main.equals(attribute[i])) {
					flag = false;
					break;
				}
			}
		}else {
			flag = false;
		}
		
		if(main!=""&&flag == true) {
			/**Start the process**/
			Association.getAssociation(Integer.parseInt(region),attribute,main);
			
			String colName = "Evap,Precp,Hum,Pres,Sundur,Temp,WS,WD";
			request.setAttribute("region", Region.getRegion(Integer.parseInt(region)));
			request.setAttribute("main", LIST.matchList(main));
			request.setAttribute("mainNormal", LIST.matchNormal(main));
			request.setAttribute("integration",LIST.integration);
			request.setAttribute("index",LIST.index);
			request.setAttribute("association",LIST.association);
			request.setAttribute("date", LIST.date);
			request.setAttribute("relation", LIST.relation);
			request.setAttribute("intergratedAttribute", LIST.rest);
			request.setAttribute("Norm",LIST.Norm);
			request.setAttribute("Column",colName);
			request.setAttribute("mainName", main);
			
			request.getRequestDispatcher("./Visualization.jsp").forward(request, response);
			LIST.clear();
		}else {
			request.getRequestDispatcher("./ReSelection.jsp").forward(request, response);
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("Run time for whole process: "+(endTime-startTime)+"ms");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
