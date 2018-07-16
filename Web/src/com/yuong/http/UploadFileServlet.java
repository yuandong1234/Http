package com.yuong.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.yuong.http.bean.BaseResponse;

/**
 * Servlet implementation class UploadFileServlet
 */
@WebServlet("/UploadFileServlet")
public class UploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String MEDIA_TYPE_STREAM="application/octet-stream";
	private static final String MEDIA_TYPE_MULTIPART="multipart/form-data";

	private File mSaveFile;
	private File mTempFile;

	private String mMessage;
	private PrintWriter mWriter;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub

		String savePath = "D:\\yuandong\\image";
		mSaveFile = new File(savePath);
		//临时保存文件夹
		String tempSavePath = "D:\\yuandong\\image\\temp";
		mTempFile = new File(tempSavePath);

		//判断上传文件的保存目录是否存在
		if (!mSaveFile.exists() && !mSaveFile.isDirectory()) {
			System.out.println(savePath+"目录不存在，需要创建");
			//创建目录
			mSaveFile.mkdir();
		}
		if (!mTempFile.exists() && !mTempFile.isDirectory()) {
			System.out.println(tempSavePath+"目录不存在，需要创建");
			//创建目录
			mTempFile.mkdir();
		}
		super.init();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadFileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

	
		String contentType=request.getHeader("content-type");
		System.out.println("contentType : "+contentType);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		mWriter=response.getWriter();
		
		if(contentType!=null&&!contentType.equals("")) {
			if(contentType.equals(MEDIA_TYPE_STREAM)||contentType.contains(MEDIA_TYPE_STREAM)) {
				doStreamData(request, response);	
			}else if(contentType.equals(MEDIA_TYPE_MULTIPART)||contentType.contains(MEDIA_TYPE_MULTIPART)) {
				doMultipartData(request, response);	
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		//request.setAttribute("message",message);
		//request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	private void doStreamData(HttpServletRequest request, HttpServletResponse response)  {
		InputStream in;
		try {
			in = request.getInputStream();
			long time =System.currentTimeMillis();
			String fileName =time+".jpg";
			//创建一个文件输出流
			FileOutputStream out = new FileOutputStream(new File(mSaveFile,fileName));
			//创建一个缓冲区
			byte data[] = new byte[1024];
			//判断输入流中的数据是否已经读完的标识
			int len = 0;
			//循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
			while((len=in.read(data))>0){
				//使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
				out.write(data, 0, len);
			}
			//关闭输入流
			in.close();
			//关闭输出流
			out.close();
			mMessage = "upload file success !";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mMessage = "upload file fail !";
		}finally {
			onSuccess(response);
		}
	}

	private void doMultipartData(HttpServletRequest request, HttpServletResponse response) {

		try{
			//使用Apache文件上传组件处理文件上传步骤：
			//1、创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(mTempFile);        //设置临时文件夹为TEMP_FOLDER
			factory.setSizeThreshold(1024 * 1024);    // 设置缓冲区大小为 1M
			//2、创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			//解决上传文件名的中文乱码
			upload.setHeaderEncoding("UTF-8"); 
			//3、判断提交上来的数据是否是上传表单的数据
			if(!ServletFileUpload.isMultipartContent(request)){
				//按照传统方式获取数据
				System.out.println("post data is not Multipart content !");
				return;
			}
			//4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
			List<FileItem> list = upload.parseRequest(request);
			for(FileItem item : list){
				//如果fileitem中封装的是普通输入项的数据
				if(item.isFormField()){
					String name = item.getFieldName();
					//解决普通输入项的数据的中文乱码问题
					String value = item.getString("UTF-8");
					//value = new String(value.getBytes("iso8859-1"),"UTF-8");
					System.out.println(name + "=" + value);
				}else{//如果fileitem中封装的是上传文件
					//得到上传的文件名称，
					String filename = item.getName();
					System.out.println(filename);
					if(filename==null || filename.trim().equals("")){
						continue;
					}
					//注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
					//处理获取到的上传文件的文件名的路径部分，只保留文件名部分
					filename = filename.substring(filename.lastIndexOf("\\")+1);
					//					//获取item中的上传文件的输入流
					//					InputStream in = item.getInputStream();
					//					//创建一个文件输出流
					//					FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
					//					//创建一个缓冲区
					//					byte buffer[] = new byte[1024];
					//					//判断输入流中的数据是否已经读完的标识
					//					int len = 0;
					//					//循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
					//					while((len=in.read(buffer))>0){
					//						//使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
					//						out.write(buffer, 0, len);
					//					}
					//					//关闭输入流
					//					in.close();
					//					//关闭输出流
					//					out.close();
					//					//删除处理文件上传时生成的临时文件
					//					item.delete();

					item.write(new File(mSaveFile,filename));
					mMessage = "upload file success !";
				}
			}
		}catch (Exception e) {
			mMessage= "upload file fail !";
			e.printStackTrace();
		}finally {
			//mWriter.append(mMessage);
			onSuccess(response);
		}
	}

	private void  onSuccess(HttpServletResponse response) {
	
		BaseResponse baseResponse=new BaseResponse();
		baseResponse.setCode(200);
		baseResponse.setMessage(mMessage);
		Gson gson=new Gson();
		String json=gson.toJson(baseResponse);
		mWriter.append(json);
	}
}
