package impl;

import obj.Student;
import manager.StudentManager;
import util.StudentManagerTool;

/**
 * Created by Administrator on 2018/7/6.
 */
public class StudentManagerDriver {

    static {

        //TODO:在这里实现这个接口
        StudentManagerTool tool=new StudentManagerTool() {

            @Override
            public Student createStudent(String studentNum, String studentName, String sex, String grade, String classNum) {
                return null;
            }

            @Override
            public Student updateStudent(String parameterName, String value, Student student) {
                return null;
            }

            @Override
            public String getOperation(String parameterName) {
                return null;
            }

            @Override
            public String getStudentNum(String parameterName) {
                return null;
            }
        };

        //注册学生管理工具
        StudentManager.registerTool(tool);
    }

}
