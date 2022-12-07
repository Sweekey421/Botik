package com.example.demo;

import java.util.*;
import java.util.regex.*;

public class SimpleBot {
    public static int getRandomDiceNumber()
    {
        return (int) (Math.random() * 4);
    }

    public static void main(String[] args) {
        new SimpleChatBot();
        for (int i = 0; i < 1; i++)
        {
            int x = getRandomDiceNumber();
            if (x == 1) {
                System.out.println("Привет");
            }else if(x == 2){
                System.out.println("Как дела?");
            }else if (x == 3){
                System.out.println("Что делаешь?");
            }
        }
    }
    final String[] COMMON_PHRASES = {
            "Нет ничего ценнее слов, сказанных к месту и ко времени.",
            "Порой молчание может сказать больше, нежели уйма слов.",
            "Перед тем как писать/говорить всегда лучше подумать.",
            "Вежливая и грамотная речь говорит о величии души.",
            "Приятно когда текст без орфографических ошибок.",
            "Многословие есть признак неупорядоченного ума.",
            "Слова могут ранить, но могут и исцелять.",
            "Записывая слова, мы удваиваем их силу.",
            "Кто ясно мыслит, тот ясно излагает.",
            "Боюсь Вы что-то не договариваете."};
    final String[] ELUSIVE_ANSWERS = {
            "Вопрос непростой, прошу тайм-аут на раздумья.",
            "Не уверен, что располагаю такой информацией.",
            "Может лучше поговорим о чём-то другом?",
            "Простите, но это очень личный вопрос.",
            "Не уверен, что Вам понравится ответ.",
            "Поверьте, я сам хотел бы это знать.",
            "Вы действительно хотите это знать?",
            "Уверен, Вы уже догадались сами.",
            "Зачем Вам такая информация?",
            "Давайте сохраним интригу?"};
    final Map<String, String> PATTERNS_FOR_ANALYSIS = new HashMap<String, String>() {{

        put("хай", "hello");
        put("привет", "hello");
        put("здорово", "hello");
        put("здравствуй", "hello");
        put("ку", "hello");
        put("привки", "hello");
        put("прив", "hello");

        put("кто\\s.*ты", "who");
        put("ты\\s.*кто", "who");

        put("как\\s.*зовут", "name");
        put("как\\s.*имя", "name");
        put("есть\\s.*имя", "name");
        put("какое\\s.*имя", "name");
        put("твоё\\s.*имя", "name");

        put("как\\s.*дела", "howareyou");
        put("как\\s.*жизнь", "howareyou");
        put("как\\s.*настроение", "howareyou");

        put("зачем\\s.*тут", "whatdoyoudoing");
        put("чем\\s.*занят", "whatdoyoudoing");
        put("зачем\\s.*здесь", "whatdoyoudoing");
        put("что\\s.*делаешь", "whatdoyoudoing");
        put("чем\\s.*занимаешься", "whatdoyoudoing");

        put("что\\s.*нравится", "whatdoyoulike");
        put("что\\s.*любишь", "whatdoyoulike");

        put("кажется", "iamfeelling");
        put("чувствую", "iamfeelling");
        put("испытываю", "iamfeelling");

        put("да", "yes");
        put("конечно", "yes");
        put("именно", "yes");
        put("согласен", "yes");

        put("который\\s.*час", "whattime");
        put("сколько\\s.*время", "whattime");
        put("время", "whattime");

        put("прощай", "bye");
        put("пока", "bye");
        put("увидимся", "bye");
        put("до\\s.*свидания", "bye");
        put("до\\s.*встречи", "bye");
    }};
    final Map<String, String> ANSWERS_BY_PATTERNS = new HashMap<String, String>() {{
        put("hello", "Здравствуйте, рад Вас видеть.");
        put("who", "Я обычный чат-бот.");
        put("name", "Зовите меня Аркаша :)");
        put("howareyou", "Спасибо, что интересуетесь. У меня всё хорошо.");
        put("whatdoyoudoing", "Я пробую общаться с людьми.");
        put("whatdoyoulike", "Мне нравиться думать что я не просто программа.");
        put("iamfeelling", "Как давно это началось? Расскажите чуть подробнее.");
        put("yes", "Согласие есть продукт при полном непротивлении сторон.");
        put("bye", "До свидания. Надеюсь, ещё увидимся.");
    }};
    Pattern pattern;
    Random random;
    Date date;

    public SimpleBot() {
        random = new Random();
        date = new Date();
    }

    public String sayInReturn(String msg, boolean ai) {
        String say = (msg.trim().endsWith("?"))?
                ELUSIVE_ANSWERS[random.nextInt(ELUSIVE_ANSWERS.length)]:
                COMMON_PHRASES[random.nextInt(COMMON_PHRASES.length)];
        if (ai) {
            String message =
                    String.join(" ", msg.toLowerCase().split("[ {,|.}?]+"));
            for (Map.Entry<String, String> o : PATTERNS_FOR_ANALYSIS.entrySet()) {
                pattern = Pattern.compile(o.getKey());
                if (pattern.matcher(message).find())
                    if (o.getValue().equals("whattime"))
                        return date.toString();
                    else return ANSWERS_BY_PATTERNS.get(o.getValue());
            }
        }
        return say;
    }
}