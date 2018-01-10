public class Student {
    private String name;
    private String addr;

    public Student() {
    }

    public Student(String name, String addr) {
        this.name = name;
        this.addr = addr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;

        Student student = (Student) o;

        if (!name.equals(student.name)) return false;
        return addr.equals(student.addr);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + addr.hashCode();
        return result;
    }
}
