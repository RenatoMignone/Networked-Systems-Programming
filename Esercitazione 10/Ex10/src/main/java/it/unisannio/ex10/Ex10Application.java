    package it.unisannio.ex10;

    import it.unisannio.ex10.Ex10_1.Strings;
    import it.unisannio.ex10.Ex10_2.User_Controller;
    import it.unisannio.ex10.Ex10_3.Book_Controller;
    import jakarta.ws.rs.ApplicationPath;
    import org.glassfish.jersey.server.ResourceConfig;
    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;

    @SpringBootApplication
    @ApplicationPath("rest")
    public class Ex10Application extends ResourceConfig {

        public Ex10Application(){
            register(new User_Controller());
            register (new Strings());
            register (new Book_Controller());
        }
        public static void main(String[] args) {
            SpringApplication.run(Ex10Application.class, args);
        }


    }
