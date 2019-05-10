package xxx;

/**
 * @author: hh
 * @create: 2019/4/29 11:14
 **/

public enum  MyEnum {


    One(1,"春天");

    private Integer enumCode;
    private String enumMessage;

    public Integer getEnumCode() {
        return enumCode;
    }

    public String getEnumMessage() {
        return enumMessage;
    }

    MyEnum(Integer enumCode, String enumMessage) {
        this.enumCode = enumCode;
        this.enumMessage = enumMessage;
    }

    public static  MyEnum forEach_MyEnum(int index){

        MyEnum[] myEnums = MyEnum.values();
        for (MyEnum myEnum : myEnums) {
            if(index==myEnum.getEnumCode()){
                return myEnum;
            }
        }

        return null;

    }
}
