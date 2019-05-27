package datastructure.sxt.linetable;

/**
 * 自定义异常
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-05-23-上午 10:41
 */
public class MyArrayIndexOutOfBoundsException extends RuntimeException {

    private static final long serialVersionUID = -5560388797387641327L;

    public MyArrayIndexOutOfBoundsException() {

        super();
    }

    public MyArrayIndexOutOfBoundsException(String message) {

        super(message);
    }
}
