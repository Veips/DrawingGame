

public class Application {
    public static void main(String[] args) throws Exception{
        Connect connectWindow = new Connect();
        CreateServer createServer = new CreateServer();
        ServerConsole serverConsole = new ServerConsole();
        Game game = new Game();

        connectWindow.init();

        createServer.init();

        serverConsole.init();

        game.init();

    }
}
