package com.example.test;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class TestApplication {

    public static class Question {
        public String question;
        public List<Answer> answers;

        public List<String> getCorrectAnswers() {
            return answers.stream().filter(answer -> answer.correct).map(answer -> "" + (answers.indexOf(answer) + 1)).toList();
        }

        public Question(String question, Answer... answers) {
            this.question = question;
            this.answers = List.of(answers);
        }

        public static class Answer {
            public String text;
            public boolean correct;

            public Answer(String text, boolean correct) {
                this.text = text;
                this.correct = correct;
            }
        }
    }


    public static void main(String[] args) {
        List<Question> questions = getQuestions();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Привет! Добро пожаловать в текстовый тест.");
        System.out.println("Ответьте на следующие вопросы:%n");
        AtomicInteger score = new AtomicInteger();

        questions.forEach(question -> {
            System.out.printf("%n%s. %s%n%n", questions.indexOf(question) + 1, question.question);
            question.answers.forEach(answer -> {
                System.out.printf("%s) %s%n", question.answers.indexOf(answer) + 1, answer.text);
            });
            System.out.println("Ваш ответ (если правильных вариантов несколько - вводите через запятую):");


            List<String> correctAnswers = question.getCorrectAnswers();

            List<String> answers = Stream.of(scanner.nextLine().split(",")).filter(Objects::nonNull).toList();

            boolean areEqual = new HashSet<>(answers).equals(new HashSet<>(correctAnswers));

            if (areEqual) {
                score.getAndIncrement();
            }
        });

        // Результат
        System.out.println("\nВаш результат: " + score + " из " + questions.size());

        if (score.get() == questions.size()) {
            System.out.println("Отлично! Вы все правильно ответили!");
        } else if (score.get() > questions.size() * 3 / 4) {
            System.out.println("Хорошо! Вы ответили правильно на " + score + " вопрос(ов).");
        } else if (score.get() > questions.size() / 2) {
            System.out.println("Удовлетворительно. Вы ответили правильно на " + score + " вопрос(ов).");
        } else {
            System.out.println("К сожалению, незачет.");
        }

        scanner.close();
    }

    private static List<Question> getQuestions() {
        return List.of(
                new Question("Что такое система?",
                        new Question.Answer("Набор взаимосвязанных элементов, обладающих целостностью", true),
                        new Question.Answer("Случайное объединение объектов", false),
                        new Question.Answer("Совокупность ресурсов, работающих на достижение общей цели", true),
                        new Question.Answer("Набор независимых процессов", false)
                ),
                new Question("Какие из следующих свойств не относится к системам?",
                        new Question.Answer("Целостность", false),
                        new Question.Answer("Изолированность", true),
                        new Question.Answer("Эмерджентность", false),
                        new Question.Answer("Динамичность", false)
                ),
                new Question("Какие из следующих методов системного анализа включает в себя оценку внешней среды?",
                        new Question.Answer("SWOT-анализ", true),
                        new Question.Answer("PEST-анализ", true),
                        new Question.Answer("Метод морфологического анализа", false),
                        new Question.Answer("Метод анализа иерархий", false)
                ),
                new Question("Какие из следующих этапов не является частью системного анализа?",
                        new Question.Answer("Определение потребностей в ресурсах", false),
                        new Question.Answer("Сбор данных о внешней среде", false),
                        new Question.Answer("Разработка программного обеспечения", true),
                        new Question.Answer("Выявление альтернатив достижения цели", false)
                ),
                new Question("Что такое модель «черного ящика»?",
                        new Question.Answer("Модель, показывающая внутренние процессы системы", false),
                        new Question.Answer("Модель, которая рассматривает систему только по входам и выходам", true),
                        new Question.Answer("Модель, использующаяся для упрощения анализа системы", true),
                        new Question.Answer("Модель, описывающая структуру системы", false)
                ),
                new Question("Какие из утверждений о циклах обратной связи верно?",
                        new Question.Answer("Они могут быть как усиливающими, так и уравновешивающими", true),
                        new Question.Answer("Они всегда усиливающие", false),
                        new Question.Answer("Они могут быть внешними и внутренними", true),
                        new Question.Answer("Они не влияют на устойчивость системы", false)
                ),
                new Question("Какой метод используется для анализа альтернатив?",
                        new Question.Answer("Метод SWOT-анализа", false),
                        new Question.Answer("Метод морфологического анализа", false),
                        new Question.Answer("Метод анализа иерархий", true),
                        new Question.Answer("Метод многокритериального анализа", true)
                ),
                new Question("Что такое факторный анализ?",
                        new Question.Answer("Метод, который не имеет четкой структуры", false),
                        new Question.Answer("Метод, который изучает влияние отдельных факторов на результирующий показатель", true),
                        new Question.Answer("Метод, который применяется только в экономике", false),
                        new Question.Answer("Метод, позволяющий выделить ключевые факторы из множества переменных", true)
                ),
                new Question("Какие из методов является частью стратегического системного анализа?",
                        new Question.Answer("PEST-анализ", true),
                        new Question.Answer("SWOT-анализ", true),
                        new Question.Answer("Метод Дельфи", false),
                        new Question.Answer("Метод сценарного планирования", true)
                ),
                new Question("Что такое жизненный цикл проекта развития организации?",
                        new Question.Answer("Процесс, который включает в себя стадии от идеи до завершения проекта", true),
                        new Question.Answer("Последовательность действий, необходимых для достижения целей проекта", true),
                        new Question.Answer("Метод, который используется только для крупных проектов", false),
                        new Question.Answer("Время, необходимое для выполнения одного этапа проекта", false)
                )
        );
    }

}
