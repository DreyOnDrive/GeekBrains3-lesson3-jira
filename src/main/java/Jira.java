public class Jira {

    public static void main(String[] args) throws InterruptedException {
        Login.main(args);
        MakeEpic.main(args);
        MakeTeam.main(args);

        System.out.println();

        if (Login.loginSuccess & MakeEpic.makeEpicSuccess & MakeTeam.makeTeamSuccess){
            System.out.println("Тест-сет выполнен успешно");
        } else {
            System.out.println("Тест-сет провален");
        }
    }

}
