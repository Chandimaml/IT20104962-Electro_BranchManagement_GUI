package com;

import java.io.IOException;
import java.util.Map;
 
import java.util.HashMap;
 
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BranchesAPI
 */
@WebServlet("/BranchesAPI")
public class BranchesAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private Object branchObj;
	Branch BranchObj = new Branch();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BranchesAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		{
			 
			String output = BranchObj.insertBranch(request.getParameter("BranchName"),
			request.getParameter("Category"),
			request.getParameter("Location"),
			request.getParameter("PowerCapacity"),
			request.getParameter("HeadEngineer"));
			response.getWriter().write(output);
			doGet(request, response);
			}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		{
			Map paras = getParasMap(request);
			String output = BranchObj.updateBranch(paras.get("hidBranchIDSave").toString(),
			paras.get("BranchName").toString(),
			paras.get("Category").toString(),
			paras.get("Location").toString(),
			paras.get("PowerCapacity").toString(),
			paras.get("HeadEngineer").toString());
			response.getWriter().write(output);
			}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		{
			Map paras = getParasMap(request);
			String output = BranchObj.deleteBranch(paras.get("BranchID").toString());
			response.getWriter().write(output);
			}
	}
	private static Map getParasMap(HttpServletRequest request)
	{
	Map<String, String> map = new HashMap<String, String>();
	try
	{
	Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	String queryString = scanner.hasNext() ?
	scanner.useDelimiter("\\A").next() : "";
	scanner.close();
	String[] params = queryString.split("&");
	for (String param : params)
	{
		String[] p = param.split("=");
		map.put(p[0], p[1]);
		}
		}
		catch (Exception e)
		{
		}
		return map;
		}
	

}


