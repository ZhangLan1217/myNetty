package protocol;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageProtocol {
    private  int len;
    private byte[] content;
}
