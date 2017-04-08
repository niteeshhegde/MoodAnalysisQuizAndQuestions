package abc.moodanalysis;

/**
 * Created by niteesh on 4/8/2017.
 */


public class blog {
    private String Description,Image,Title,Name;

    public blog(){

    }
    public blog(String title,String description,String image,String name){
        this.Title=title;
        this.Description=description;
        this.Image=image;
        this.Name=name;
    }
    public String getDescription(){
        return Description;
    }
    public String getTitle(){
        return Title;
    }
    public String getImage(){
        return Image;
    }
    public String getName(){
        return Name;
    }
    public void setImage(String image){
        this.Image=image;
    }
    public void setDescription(String description){
        this.Description=description;
    }
    public void setTitle(String title){
        this.Title=title;
    }
    public void setName(String name){
        this.Name=name;
    }
}