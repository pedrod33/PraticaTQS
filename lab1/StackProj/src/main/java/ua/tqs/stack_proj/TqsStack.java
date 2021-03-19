package ua.tqs.stack_proj;

import java.util.LinkedList;
public class TqsStack 
{
    private LinkedList<String> newLinkedList = new LinkedList<>();
    private int limit;
    public TqsStack(LinkedList<String> linkList){
        newLinkedList=linkList;
        this.limit=-1;
    }

    public TqsStack(LinkedList<String> linkedList, int limit){
        newLinkedList=linkedList;
        this.limit=limit;
    }

    public boolean isEmpty(){
        try {
            return this.newLinkedList.isEmpty();
        } catch (NullPointerException e) {
            return true;
        }
    }

    public String pop(){
        try{
            return this.newLinkedList.pop();
        }
        catch(NullPointerException e){
            return "This structure is empty, no element to be popped.";
        }
    }

    public String peek(){
        try{
            return this.newLinkedList.peek();
        }
        catch(NullPointerException e){
            return "This structure is empty, no element to be peeked.";
        }    
    }

    public void push(String newVal){
        if(this.limit==this.size() && this.limit!=-1){
            return;
        }
        this.newLinkedList.push(newVal);
    }

    public int size(){
        return this.newLinkedList.size();
    }
}
