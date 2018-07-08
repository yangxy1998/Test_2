package obj;

/**
 * 学生类
 *
 * Created by Administrator on 2018/7/6.
 */
public class Student {

    //学号
    private String studentNum;

    //学生姓名
    private String studentName;

    //性别
    private String sex;

    //年级
    private String grade;

    //班级号
    private String classNum;

    /**
     * 获得这个学生的学号
     * @return 学号
     */
    public String getStudentNum() {
        return studentNum;
    }

    /**
     * 设置这个学生的学号
     * @param studentNum 学号
     */
    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }


    /**
     * 获得这个学生的姓名
     * @return 姓名
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * 设置这个学生的姓名
     * @param studentName 姓名
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * 获得这个学生的性别
     * @return 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置这个学生的性别
     * @param sex 性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获得这个学生的年级
     * @return 年级
     */
    public String getGrade() {
        return grade;
    }

    /**
     * 设置这个学生的年级
     * @param grade 年级
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * 获得这个学生的班级号
     * @return 班级号
     */
    public String getClassNum() {
        return classNum;
    }

    /**
     * 设置这个学生的班级号
     * @param classNum 班级号
     */
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

}
