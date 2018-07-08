package util;

import obj.Student;

/**
 * 学生管理工具
 *
 * Created by Administrator on 2018/7/6.
 */
public interface StudentManagerTool {

    /**
     * 创建一个新学生
     * @param studentNum 学号
     * @param studentName 姓名
     * @param sex 性别
     * @param grade 年级
     * @param classNum 班号
     * @return 新创建的学生
     */
    Student createStudent(String studentNum,String studentName,String sex,String grade,String classNum);

    /**
     * 修改指定学生的信息
     * @param parameterName 要修改的学生信息名，如studentName就表示要修改这个学生的姓名
     * @param value 修改信息值，如 张三
     * @param student 传入的学生
     * @return 修改后的学生
     */
    Student updateStudent(String parameterName,String value,Student student);

    /**
     * 从参数名当中提取操作名，如从参数create->2018302580001中提取出create字符串
     * @param parameterName 参数名
     * @return 操作名
     */
    String getOperation(String parameterName);

    /**
     * 从参数名当中提取学号，如从参数create->2018302580001中提取出2018302580001
     * @param parameterName 参数名
     * @return 学号
     */
    String getStudentNum(String parameterName);

}
