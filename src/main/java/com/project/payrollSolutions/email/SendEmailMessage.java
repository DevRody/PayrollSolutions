package com.project.payrollSolutions.email;

public class SendEmailMessage {
    public static String loginSubject(String name) {
        return name + " sua nova conta foi criada!";
    }

    public static String loginText(String name, String document, String password) {
        return "Olá " + name + "! Seja bem-vindo(a) em nossa plataforma." +
                " Suas credenciais de acesso ao sistema estão logo abaixo. \n\n" +
                "========================================================= \n\n" +
                "Documento: " + document + "\n" +
                "Senha: " + password + "\n\n" +
                "========================================================= \n\n" +
                "Qualquer dúvida é só entrar em contato conosco.";
    }

    public static String feedbackSubject(String name) {
        return name + " sua nova conta foi criada!";
    }

    public static String feedbackText(String name, String document) {
        return "Olá novamente" + name + "! \n" +
                "Esperamos que esteja gostando de utilizar nossa plataforma. \n" +
                "Se possivel, nos de um feedback respondendo esse e-mail, será muito útil para nós! \n\n";
    }
}
