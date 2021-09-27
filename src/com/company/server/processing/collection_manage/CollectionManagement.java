package com.company.server.processing.collection_manage;

import com.company.server.InputDevice;
import com.company.common.collection_objects.*;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;


public class CollectionManagement {
    private LinkedHashSet<StudyGroup> collection;
    public LocalDateTime CreationDate = LocalDateTime.now();
    private PrintStream out;

    public CollectionManagement(PrintStream printStream) {
        this.collection = new LinkedHashSet<>();
        out = printStream;
    }

    public CollectionManagement() {
        this(System.out);
    }

    public void setPrintStream(PrintStream printStream) {
        this.out = printStream;
    }

    public LinkedHashSet<StudyGroup> getCollection() {
        return collection;
    }

    public String clear() {
        collection.clear();
        return "Произошла очистка коллекции";
    }
    public String info() {
        String info;
        info = this.getClass().getTypeName();
        info += " | ";
        info += this.CreationDate;
        info += " | ";
        info += String.valueOf(this.collection.size());
        info += " | ";
        return info;
    }
/*
    public String show() {
        StringBuilder showStr = new StringBuilder();
        for (StudyGroup studyGroup : collection) {
            showStr.append(
                    "\n\nStudyGroup \nid: ").append(studyGroup.getid())
                    .append("\nStudyCounter: ").append(studyGroup.getStudentsCount())
                    .append("\nCoordinates\n X: ").append(studyGroup.getCoordinates().getX())
                    .append("\n Y: ").append(studyGroup.getCoordinates().getY())
                    .append("\nAdmin Name: ").append(studyGroup.getGroupAdmin().getName())
                    .append("\nAdmin Passport: ").append(studyGroup.getGroupAdmin().getPassportID())
                    .append("\nAdmin Location:\n X: ").append(studyGroup.getGroupAdmin().getLocation().getX())
                    .append("\n Y: ").append(studyGroup.getGroupAdmin().getLocation().getY())
                    .append("\n Z: ").append(studyGroup.getGroupAdmin().getLocation().getZ())
                    .append("\nCreation Date: ").append(studyGroup.getCreationDate())
                    .append("\nName: ").append(studyGroup.getName())
                    .append("\nForm of education: ").append(studyGroup.getFormOfEducation())
                    .append("\nSemester: ").append(studyGroup.getSemesterEnum());
        }
        return showStr.toString();
    }
*/
    public void add() {
        this.add(InputDevice.input());
    }

    public void add(String CommandArgs) {
        this.add(InputDevice.inputFromFile(CommandArgs));
    }

    public String add(StudyGroup group) {
        collection.add(group);
        return "В коллекцию добавлена группа: " + group;
    }

    public String edit() {
        //TODO
        //InputDevice.edit();
        return "";
    }

    public void edit(StudyGroup studyGroup, String CommandArgs) {
        InputDevice.editFromFile(studyGroup, CommandArgs);
    }

}

