package kr.co.company.pocketmoney;

public class MoneyItem {

    private String io;
    private String date;
    private String money;
    private String content;

    public MoneyItem (String IO, String Date, String Money, String Content) {
        io = IO;
        date = Date;
        money = Money;
        content = Content;
    }

    public String getIO() {
        return io;
    }

    public void setIO(String IO) {
        io = IO;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String Date) {
        date = Date;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String Money) {
        money = Money;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}