package pers.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pers.db.rFile;
import pers.pre.change.Change;
import pers.pre.change.LIST;
import pers.pre.clean.Clean;
import pers.util.association.Association;
import pres.util.tool.Processingstr;

/**
 * Servlet implementation class VisualizeGeneral
 */
@WebServlet("/VisualizeGeneral")
public class VisualizeGeneral extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizeGeneral() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long startTime = System.currentTimeMillis();
		
		/**Receive the incoming data from the interface**/
		String file = request.getParameter("FileName");
		String colName = request.getParameter("ColName");
		String[] col = colName.split(",");
		int Main = Integer.valueOf(request.getParameter("Main"));
		int[] attri = Processingstr.strToint(request.getParameter("Associate").split(","));
		int Anomaly = Integer.valueOf(request.getParameter("Anomaly"));
		List<List<String>> content = rFile.readFile(new File(file));
		List<List<Double>>list = Clean.cleandata(content,Anomaly);
		LIST.Norm = list;
		
		List<List<Integer>> table = new ArrayList<>();
		for(int i=0;i<list.size();i++) {
			List<Integer> row = Change.change(list.get(i));
			table.add(row);
		}
		Association.getAssociation(table, col,attri,Main);
		
		request.setAttribute("main", table.get(Main));
		request.setAttribute("integration",LIST.integration);
		request.setAttribute("index",LIST.index);
		request.setAttribute("association",LIST.association);
		request.setAttribute("date", LIST.date);
		request.setAttribute("relation", LIST.relation);
		request.setAttribute("mainNormal", LIST.Norm.get(Main));
		request.setAttribute("region", col[Main]);
		request.setAttribute("Norm",list);
		request.setAttribute("Column",colName);
		request.setAttribute("intergratedAttribute", LIST.rest);
		request.setAttribute("mainName", col[Main]);
		
		request.getRequestDispatcher("./Visualization.jsp").forward(request, response);
		LIST.clear();
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
