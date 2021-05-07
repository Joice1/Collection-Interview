package com.joice;

import com.joice.domain.User;
import org.junit.Test;

import java.util.*;

/**
 * @Author: Joice
 *
 * 已知一个 HashMap<Integer，User>集合， User 有 name（String）和 age（int）属性
 * 请写一个方法实现对HashMap 的排序功能，该方法接收 HashMap<Integer，User>为形参，返
 * 回类型为 HashMap<Integer，User>，要求对 HashMap 中的 User 的 age 倒序进行排序
 * 排序时 key=value 键值对不得拆散。
 *
 * 注意：
 *      这个程序需要熟练集合的体系结构，HashMap本身就是不可排序的，但是该题偏偏让其排序
 *      那就得想API中有没有这样得Map结构是有序的
 *              ==> LinkedHashMap(),是链表结构有序的，它是HashMap的子类返回
 *                  一个LinkedHashMap对象即可，还符合面向接口编程的思想
 *      另外，在实际开发中，只要是对集合的操作，能用JDK中的API就用JDK中的API，比如排序
 *      算法，不应该去用冒泡/选择，首先想到的是Collections工具类中的方法
 */
public class HashMapTest {

    @Test
    //测试
    public void main(){

        //创建一个HashMap集合
        HashMap<Integer,User> hashMap = new HashMap<Integer,User>();

        //往HashMap中添加元素
        hashMap.put(1,new User("张三",25));
        hashMap.put(3,new User("李四",22));
        hashMap.put(2,new User("王五",28));

        //未排序之前
        System.out.println(hashMap);

        //调用方法进行遍历
        HashMap<Integer, User> map = HashMapTest.sortHashMap(hashMap);
        System.out.println(map);
    }

    public static HashMap<Integer, User> sortHashMap(HashMap<Integer,User> map){

        //1、将map集合转换为set集合
        Set<Map.Entry<Integer, User>> set = map.entrySet();

        //2、将set集合转换为List集合，为了使用工具类Collections进行排序
        List<Map.Entry<Integer,User>> list = new ArrayList<Map.Entry<Integer, User>>(set);

        //3、调用工具类Collections的Sort方法进行排序,排序规则通过匿名内部类实现
        Collections.sort(list,new Comparator<Map.Entry<Integer,User>>(){
            public int compare(Map.Entry<Integer,User> o1,Map.Entry<Integer,User> o2){
                //4、根据要求按照User中的Age倒序排列
                return o1.getValue().getAge() - o2.getValue().getAge();
            }
        });

        //5、创建一个新的LinkedHashMap集合，将list集合中的数据以键值对的形式存入
        LinkedHashMap<Integer,User> linkedHashMap = new LinkedHashMap<Integer,User>();

        //6、遍历list集合，将元素存入linkedHashMap集合
        for(Map.Entry<Integer,User> entry : list){
            linkedHashMap.put(entry.getKey(),entry.getValue());
        }

        //7、返回结果
        return linkedHashMap;
    }
}
