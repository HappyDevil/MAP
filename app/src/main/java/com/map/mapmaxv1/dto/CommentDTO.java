package com.map.mapmaxv1.dto;

import java.util.Date;

/**
 * Created by User on 05.06.2017.
 */

public class CommentDTO {

    private String text; // Текст отзыва
    private int rait; // Оценка мастера, прикрепленная к отзыву
    private Date date; // Дата написания отзыва

    // Вся остальныая инфа из профиля писавшего, и нужно разобраться как хранить дату (мб в стринге)
}
