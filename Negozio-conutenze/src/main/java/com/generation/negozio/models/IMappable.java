package com.generation.negozio.models;

import java.lang.reflect.Method;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public interface IMappable {

    default Map<String,String> toMap(){
        Map<String,String> ris=new HashMap<>();
        String keyProp="";
        String valueProp="";
        boolean check=false;
        for(Method m : this.getClass().getMethods()){
            if(m.getName().startsWith("get")
                    && !"getClass".equals(m.getName())
                    && !m.getName().contains("List")){
                keyProp=m.getName().substring(3);
                check=true;
            } else if (m.getName().startsWith("is")) {
                keyProp=m.getName().substring(2);
                check=true;
            }
            if(check) {
                keyProp = Character.toLowerCase(keyProp.charAt(0)) + keyProp.substring(1);
                try {
                    valueProp = String.valueOf(m.invoke(this));
                }catch (Exception e){
                    e.printStackTrace();
                    System.err.println("catch di toMap");
                }
            }
            ris.put(keyProp,valueProp);
            check=false;
        }

        return ris;
    }

    default void fromMap (Map<String,String> param){
        String nomeProp;
        String valoreProp;
        for(Method m : this.getClass().getMethods()){

            if(m.getName().startsWith("set")) {
                nomeProp=m.getName().substring(3);//tolgo il "set"

                nomeProp = Character.toLowerCase(nomeProp.charAt(0)) +
                        nomeProp.substring(1);
                if(param.containsKey(nomeProp)){
                    valoreProp=param.get(nomeProp);
                    String tipo= m.getParameters()[0].getType().getSimpleName();
                    try {
                        switch (tipo.toLowerCase()) {
                            case "long":
                                m.invoke(this,Long.parseLong(valoreProp));
                                break;
                            case "string":
                                m.invoke(this, valoreProp);
                                break;
                            case "double":
                                m.invoke(this,Double.parseDouble(valoreProp));
                                break;
                            case "int":
                                m.invoke(this,Integer.parseInt(valoreProp));
                                break;
                            case "date":
                                m.invoke(this, Date.valueOf(valoreProp));
                                break;
                            case "boolean":
                                //da sql i boolean arrivano come 0 e 1
                                m.invoke(this, valoreProp.equals("1") ||
                                        valoreProp.equalsIgnoreCase("true"));
                                break;
                            default:
                                System.err.println("Attenzione non ho riconsciuto il tipo");
                                break;
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                        System.err.println("catch invoke");
                    }
                }
            }
        }
    }



}
