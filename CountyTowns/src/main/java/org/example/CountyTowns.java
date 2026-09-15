package org.example;

import java.util.HashMap;
import java.util.Map;

public class CountyTowns {
    public static void main(String[] args) {
        Map<String, String> countyToTown = new HashMap<>();

        String[] counties = {"Bedfordshire", "Berkshire", "Bristol", "Buckinghamshire", "Cambridgeshire", "Cheshire", "Cornwall", "Cumbria", "Derbyshire", "Devon", "Dorset", "Durham", "East Riding of Yorkshire", "East Sussex", "Essex", "Gloucestershire", "Greater London", "Greater Manchester", "Hampshire", "Herefordshire", "Hertfordshire", "Isle of Wight", "Kent", "Lancashire", "Leicestershire", "Lincolnshire", "Merseyside", "Norfolk", "North Yorkshire", "Northamptonshire", "Northumberland", "Nottinghamshire", "Oxfordshire", "Rutland", "Shropshire", "Somerset", "South Yorkshire", "Staffordshire", "Suffolk", "Surrey", "Tyne and Wear", "Warwickshire", "West Midlands", "West Sussex", "West Yorkshire", "Wiltshire", "Worcestershire"};
        String[] countyTowns = {"Bedford", "Reading", "Bristol", "Aylesbury", "Alconbury Weald", "Chester", "Truro", "Carlisle", "Derby", "Exeter", "Dorchester", "Durham", "Beverley", "Lewes", "Chelmsford", "Gloucester", "Lambeth", "Manchester", "Winchester", "Hereford", "Hertford", "Newport", "Maidstone", "Preston", "Leicester", "Lincoln", "Liverpool", "Norwich", "Northallerton", "Northampton", "Morpeth", "West Bridgford", "Oxford", "Oakham", "Shrewsbury", "Taunton", "Barnsley", "Stafford", "Ipswich", "Reigate", "Newcastle upon Tyne", "Warwick", "Birmingham", "Chichester", "Wakefield", "Trowbridge", "Worcester"};

        for (int i = 0; i < counties.length; i++) {
            countyToTown.put(counties[i], countyTowns[i]);
        }

        System.out.println("COUNTIES:");
        System.out.println("=======");
        for (String county : countyToTown.keySet()) System.out.println(county);

        System.out.println();

        System.out.println("COUNTY TOWNS:");
        System.out.println("=======");
        for (String countyTown : countyToTown.values()) System.out.println(countyTown);

        System.out.println();

        System.out.println("COUNTY/COUNTY TOWN PAIRS:");
        System.out.println("=======");
        for (String county : countyToTown.keySet()) System.out.println(county + " - " + countyToTown.get(county));

    }
}