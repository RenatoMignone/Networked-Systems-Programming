    package it.unisannio.clienttester11;

    import it.unisannio.clienttester11.Ex10_1.Strings;
    import it.unisannio.clienttester11.Ex10_2.User_Controller;
    import it.unisannio.clienttester11.Ex10_3.Book_Controller;
    import jakarta.ws.rs.ApplicationPath;
    import org.glassfish.jersey.server.ResourceConfig;
    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;

    @SpringBootApplication
    @ApplicationPath("rest")
    public class ClientTester11Application extends ResourceConfig {

        public ClientTester11Application(){
            register(new User_Controller());
            register (new Strings());
            register (new Book_Controller());
        }
        public static void main(String[] args) {
            SpringApplication.run(ClientTester11Application.class, args);
        }


    }
