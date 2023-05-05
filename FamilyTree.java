/*George Sphicas December 3, 2022*/
package com.usf.cs245.a3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.*;

/**
 * FamilyTree is used to store the information about each individual member of the family.
 * Each person's name, birth and death date, relationship to another member of the family, and life events,
 * are kept. The methods addPerson, addEvent, and addRelationship, all take the inputs entered from
 * main to store the values in each person's designated object.
 */
public class FamilyTree {
    String name;
    String relationship = "";
    int bday;
    int dday;
    List<Edge> edges = Arrays.asList(new Edge(bday,dday));
    ArrayList<String> events = new ArrayList<>();

    void addPerson(String pName, int pBday, int pDday) {
        name = pName;
        bday = pBday;
        dday = pDday;
    }
    void addEvent(String pName, int pDate, String pEvent) {
        events.add(pDate + ": " + pEvent);
        Collections.sort(events);
    }

    void addRelationship(String pName, String pRelative, String pStatus) {
        relationship = pStatus;
    }

    String getLifespan() {
        return "(" + bday + "-" + dday + ")";
    }

    String getRelationship() {
        return relationship;
    }

    public String toString() {
        return name+" "+bday+" "+dday+"\n";
    }

    static class Edge{
        int start;
        int end;

        Edge(int pStart, int pEnd) {
            start = pStart;
            end = pEnd;
        }
    }

    static class Graph {
        List<List<Integer>> adjacencyList = new ArrayList<>();

        public Graph(List<Edge> edges) {
            int n = 0;
            for(Edge e: edges) {
                n = Integer.max(n,Integer.max(e.start,e.end));
            }
            for(int i = 0; i <= n; i++) {
                adjacencyList.add(i, new ArrayList<>());
            }
            for(Edge curr: edges) {
                adjacencyList.get(curr.start).add(curr.end);
            }
        }

        public static void displayGraph(Graph graph) {
            int i = 0;
            while(i < graph.adjacencyList.size()) {
                for(int j: graph.adjacencyList.get(i)) {
                    System.out.println(i + "-->" + j);
                }
                System.out.println();
                i++;
            }
        }
    }
    /**
     * This function displays all the members of the family in the desired fashion.
     * Using the relationship values of "grandchild", "child", or "spouses", entered
     * when setting up each person, the corresponding spacing is given to the output.
     *
     * @param family List of objects that holds each family member and all of their information
     */
    public static void displayTree(ArrayList<FamilyTree> family) {
        for (int x = 0; x < family.size(); x++) {
            if (family.get(x).getRelationship().equals("grandchild")) {
                System.out.println("        " + family.get(x).name + " " + family.get(x).getLifespan() + "; " + family.get(x).events.toString().substring(1, family.get(x).events.toString().length() - 1));

            } else if (family.get(x).getRelationship().equals("child")) {
                System.out.println("    " + family.get(x).name + " " + family.get(x).getLifespan() + "; " + family.get(x).events.toString().substring(1, family.get(x).events.toString().length() - 1));

            } else {
                System.out.println(family.get(x).name + " " + family.get(x).getLifespan() + "; " + family.get(x).events.toString().substring(1, family.get(x).events.toString().length() - 1));
            }
        }
    }

    void makegraph(int name, int relative) {
        List<Edge> edges = Arrays.asList(new Edge(name,relative));
        Graph graph = new Graph(edges);
    }

    /**
     * An arraylist of FamilyTree objects called "family" is created in order to store
     * all the objects in one place. After creating a FamilyTree object, it is added to this
     * list, to then be accessed and printed at the end.
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<FamilyTree> family = new ArrayList<>();

        FamilyTree p1 = new FamilyTree();
        p1.addPerson("George Sphicas", 1990, 2040);
        p1.addEvent("George Sphicas", 2010, "Founded computer science");
        p1.addEvent("George Sphicas", 1998, "Ate Pizza");
        family.add(p1);

        FamilyTree p2 = new FamilyTree();
        p2.addPerson("Kaylee Pereira", 1991, 2030);
        p2.addEvent("Kaylee Pereira", 2000, "Invented Fortnite");
        p2.addRelationship("Kaylee Pereira", "George Sphicas", "spouses");
        family.add(p2);

        FamilyTree p3 = new FamilyTree();
        p3.addPerson("Peter Piper Sphicas", 2010, 2050);
        p3.addEvent("Peter Piper Sphicas", 2035, "Became a D1 Esports Athlete");
        p3.addEvent("Peter Piper Sphicas", 2036, "Got arrested, got dropped from Esports team");
        p3.addRelationship("Peter Piper Sphicas", "George Sphicas", "child");
        family.add(p3);

        FamilyTree p4 = new FamilyTree();
        p4.addPerson("Bella Lisa Sphicas", 2011, 2090);
        p4.addEvent("Bella Lisa Sphicas", 2055, "Elected President of United States");
        p4.addEvent("Bella Lisa Sphicas", 2019, "Dropped a collaboration album with Playboi Carti");
        p4.addRelationship("Bella Lisa Sphicas", "George Sphicas", "child");
        family.add(p4);

        FamilyTree p5 = new FamilyTree();
        p5.addPerson("Sara Aubrey Sphicas", 2040, 2102);
        p5.addEvent("Sara Aubrey Sphicas", 2075, "Relaunched Papa John's Pizza");
        p5.addEvent("Sara Aubrey Sphicas", 2073, "Invented the Pizza Flavor Oreo");
        p5.addEvent("Sara Aubrey Sphicas", 2080, "Stopped liking pizza");
        p5.addRelationship("Sara Aubrey Sphicas", "George Sphicas", "grandchild");
        family.add(p5);

        FamilyTree p6 = new FamilyTree();
        p6.addPerson("Aryaman Sphicas", 2014, 2067);
        p6.addEvent("Aryaman Sphicas", 2030, "Went viral on Tiktok");
        p6.addEvent("Aryaman Sphicas", 2031, "Got cancelled for being narcissistic");
        p6.addRelationship("Aryaman Sphicas", "George Sphicas", "child");
        family.add(p6);

        FamilyTree p7 = new FamilyTree();
        p7.addPerson("Raina Sphicas", 2012, 2086);
        p7.addEvent("Raina Sphicas", 2050, "Created new health plan initiatives for school lunches");
        p7.addEvent("Raina Sphicas", 2026, "Won America's Got Talent");
        p7.addEvent("Raina Sphicas", 2028, "Won The Voice");
        p7.addRelationship("Raina Sphicas", "Kaylee Pereira", "child");
        family.add(p7);

        FamilyTree p8 = new FamilyTree();
        p8.addPerson("Tecca Sphicas", 2042, 3005);
        p8.addEvent("Tecca Sphicas", 2080, "Discovered how to live forever");
        p8.addEvent("Tecca Sphicas", 2082, "Colonized Mars and created the first city off-Earth");
        p8.addEvent("Tecca Sphicas", 3000, "Lead a war against alien inhabitants on Mars");
        p8.addRelationship("Tecca Sphicas", "Kaylee Pereira", "grandchild");
        family.add(p8);

        displayTree(family);
    }
}
