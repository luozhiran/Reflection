package itg.ch4;

public class Test {
    public String tastName;
    public String result;

    public Test() {
    }

    public Test(String testName) {
        this.tastName = testName;
    }

    public Test(String tastName, String result) {
        this.tastName = tastName;
        this.result = result;
    }

    public String getTastName() {
        return tastName;
    }

    public void setTastName(String tastName) {
        this.tastName = tastName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
