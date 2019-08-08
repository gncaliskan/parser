package com.yoncaapp.Util;

import com.yoncaapp.Model.Comment;

public class LogicUtil {

    public static boolean getActiveStatus(Comment c){
        return c.isContainLabelCount() && c.isAccepted() && c.isNotDeleted() && c.isMatched();
    }

    public static void setActiveStatus(Comment c){
        c.setAccepted(true);
        c.setNotDeleted(true);
        c.setMatched(true);
        c.setContainLabelCount(true);
    }

}
