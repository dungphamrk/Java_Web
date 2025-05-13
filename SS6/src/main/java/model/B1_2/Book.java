package model.B1_2;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {
    private String bookCode;
    private String title;
    private String author;
    private String category;
    private int quantity;


}
