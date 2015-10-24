/**
 * 
 */
package solutions.infobase.flexdata;

import solutions.infobase.flexdata.FlexData.Type;


/**
 * @author Hardy Haardt
 *
 */
public class FlexDataWrongTypeRuntimeException  extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FlexDataWrongTypeRuntimeException(Type toBe, Type actual) {
        super((new StringBuilder("Wrong type: should be:")).append(FlexData.getTypename(toBe)).append(", actual: ").append(FlexData.getTypename(actual)).toString());
    }

}
