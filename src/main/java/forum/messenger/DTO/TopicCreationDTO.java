package forum.messenger.DTO;


import javax.validation.Valid;

@Valid
public class TopicCreationDTO {

   private String name;

   public TopicCreationDTO(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
