package ca.sheridancollege.yousuf.beans;





import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Data
@NoArgsConstructor
@Getter
@Setter

public class ShowAppt {
	
	
	private int id;
	private String showName;
	private String showTime;
	private String showInfo;
	
	
	
}
