package microservice.core.person.exceptions;

public enum ErrMsg {

    ALREADY_EXIST("Already exist"),
    NOT_FOUND("Not found");

    private String desc;

    ErrMsg(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
