package manager;

import obj.Student;
import util.StudentManagerTool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 学生管理工具servlet
 * Servlet本身是给用户的请求提供响应的交互工具，响应形式一般是一个HTML页面。
 * 可以在Servlet当中插入HTML代码作为响应，但这次任务我们不这样做。
 * Created by Administrator on 2018/7/6.
 */
public class StudentManager extends HttpServlet{

    //学生管理驱动
    private static StudentManagerTool tool;

    //学生表
    private static List<Student> students;

    //是否已完成初始化
    private static boolean isInit=false;

    /**
     * 注册驱动
     * @param studentManagerTool 传递注册管理工具接口（已实现）
     */
    public static void registerTool(StudentManagerTool studentManagerTool){
        tool= studentManagerTool;
    }

    /**
     * 初始化学生管理服务servlet
     */
    @Override
    public void init(){
        if(isInit)return;
        isInit=true;
        System.out.println("服务器初始化中...");
        System.out.println("学生管理工具驱动加载中，请稍后...");
        try {
            //寻找学生管理工具驱动
            Class.forName("impl.StudentManagerDriver");
            System.out.println("学生管理工具驱动加载成功。");
            students=new ArrayList<>();
            addStudent("2018000010001", "Bob","male","2018","2");
            addStudent("2018000010002", "Carol","female","2018","6");
            addStudent("2018000010003", "Jack","male","2018","5");
            addStudent("2017000010001", "Rose","female","2017","2");
            System.out.println("服务器初始化完成。");
        } catch (ClassNotFoundException e) {
            System.err.println("错误：学生管理工具驱动加载失败，可能是未找到驱动工具。");
            e.printStackTrace();
        }
    }

    /**
     * 创建一个新学生
     * @param studentNum 学号
     * @param studentName 姓名
     * @param sex 性别
     * @param grade 年级
     * @param classNum 班号
     * @return 新创建的学生
     */
    public static void addStudent(String studentNum,String studentName,String sex,String grade,String classNum) {
        Student student=tool.createStudent(studentNum,studentName,sex,grade,classNum);
        synchronized (students){
            students.add(student);
        }
    }

    /**
     * 通过学号获取学生
     * @param studentNum 学号
     * @return 对应学号的学生
     */
    public static Student findStudent(String studentNum){
        for (Student student:students) {
            if(student.getStudentNum().equals(studentNum))return student;
        }
        return null;
    }

    /**
     * 更新一个学生信息
     * @param parameter 属性类型
     * @param value 属性值
     * @param studentNum 学生对应的学号
     */
    public static void updateStudent(String parameter,String value,String studentNum){
        Student student=findStudent(studentNum);
        students.remove(student);
        student=tool.updateStudent(parameter,value,student);
        students.add(student);
    }

    /**
     * 删除一个学生
     * @param studentNum 要删除的学生学号
     */
    public static void removeStudent(String studentNum){
        Student student=findStudent(studentNum);
        students.remove(student);
    }

    /**
     * 获取学生列表
     * @return 学生列表
     */
    public static List<Student> getStudents() {
        return students;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher diapatcher=request.getRequestDispatcher("./index.jsp");
        Enumeration<String> parameterNames=request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String parameterName=parameterNames.nextElement();
            String operation=tool.getOperation(parameterName);
            if(operation!=null){
                String studentNum=tool.getStudentNum(parameterName);
                if(operation.equals("create")){
                    request.setAttribute("update",studentNum);
                }
                else if(operation.equals("update")){
                    request.setAttribute("update",studentNum);
                }
                else if(operation.equals("remove")){
                    request.setAttribute("update","");
                    removeStudent(studentNum);
                }
                else if(operation.equals("commit")){
                    request.setAttribute("update","");
                    if(studentNum.equals("newStudent")){
                        String newStudentNum=request.getParameter("studentNum");
                        String newStudentName=request.getParameter("studentName");
                        String newSex=request.getParameter("sex");
                        String newGrade=request.getParameter("grade");
                        String newClassNum=request.getParameter("classNum");
                        addStudent(newStudentNum,newStudentName,newSex,newGrade,newClassNum);
                    }
                    else {
                        Enumeration<String> parameters=request.getParameterNames();
                        while (parameters.hasMoreElements()){
                            String parameterN=parameters.nextElement();
                            String value=request.getParameter(parameterN);
                            updateStudent(parameterN,value,studentNum);
                        }
                    }
                }
                else {
                    request.setAttribute("update","");
                }
            }
        }
        request.setAttribute("students",students);
        diapatcher.forward(request,response);
    }
}
