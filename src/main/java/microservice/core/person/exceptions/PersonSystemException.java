package microservice.core.person.exceptions;


import lombok.Getter;

@Getter
public class PersonSystemException extends Exception {

    private String param;

    public PersonSystemException(ErrMsg errMsg, String param) {
        super(errMsg.getDesc());
        this.param = param;
    }


}
