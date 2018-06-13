package home.blackharold.sweater.domain;

import javax.persistence.*;

@Entity
@Table (name = "MESSAGES")

public class Message {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    
    private Integer id;
    
    private String text;
    private String tag;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    
    public Message() {
    }
    
    public Message(String text, String tag, User user) {
        this.text = text;
        this.tag = tag;
        this.user = user;
    }
    
    public String getAuthorName(){
        return user != null ? user.getUsername() : "<none>";
    }
    
    public User getAuthor() {
        return user;
    }
    
    public void setAuthor(User user) {
        this.user = user;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public String getTag() {
        return tag;
    }
    
    public void setTag(String tag) {
        this.tag = tag;
    }
}
