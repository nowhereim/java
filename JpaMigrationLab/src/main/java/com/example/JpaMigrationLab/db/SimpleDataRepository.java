package com.example.JpaMigrationLab.db;

import com.example.JpaMigrationLab.entity.Entity;

import java.util.*;
import java.util.stream.Collectors;

abstract public class SimpleDataRepository<T extends Entity,ID extends Long> implements DataRepository<T,ID> {

    private List<T> dataList = new ArrayList<>();

    private static long index = 1L;

    //comparator의 기능은 두개의 값을 비교해서 어떤게 큰지 작은지 같은지를 알려주는 것
    private Comparator<T> idComparator = new Comparator<T>() { //Comparator는 인터페이스라서 new로 객체를 생성할 수 없음. 그래서 익명클래스로 생성
        @Override
        public int compare(T o1, T o2) {
            return Long.compare(o1.getId(),o2.getId()); //o1.getId()가 크면 1, 작으면 -1, 같으면 0
        }
    };

    //create , update

    //T는 Entity를 상속받은 클래스 가 되니까 setId가 가능해짐.
    @Override
    public T save(T data) {
        //널값을 넘기면 안되니까 널값이 넘어오면 예외를 발생시키는 것
        if(Objects.isNull(data)){ //()안에 있는 객체값이 null이면 트루 주겠다는 의미
            throw new RuntimeException("오브젝트값이 널이다!!!!!!!!!!!!!");

            //return null; //null을 리턴하면 nullpointException이 발생함 그래서 굳이 이렇게 할 필요가..?
        }

        System.out.println(data.getId());

        //db에 일단 해당 값이 있음? 없음? 확인 후에 저장
        Optional<T> prevData = dataList.stream()
                .filter(d -> d.getId().equals(data.getId())) // d.getId()는 d의 id값을 가져오는 것이고 data.getId()는 data의 id값을 가져오는 것 이 두개를 이퀄스로 비교.
                .findFirst();
        System.out.println(prevData);
        if(prevData.isPresent()) //isPresent는 값이 있으면 true 없으면 false
        {
            System.out.println("값이 있음");
            System.out.println(prevData.get());
            dataList.remove(prevData.get()); //값이 있으면 지우고
        dataList.add(data);

        } // 저장해서 업데이트를 해줌
        else {
            //unique id
            data.setId(index++); //index를 저장 후 증가 ++이 앞에 붙으면 증가 후 저장
            dataList.add(data);
        }
        System.out.println(dataList);
        return data;
    }

    //read
    @Override
    public Optional<T> findById(ID id){
        return dataList.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst();

    };

    @Override
    public List<T> findAll(){
        return dataList.stream() //stream은 데이터를 순차적으로 처리할 수 있게 해주는 것
                .sorted(idComparator) //sorted는 정렬을 해주는 것
                .collect(Collectors.toList()); //collect는 데이터를 수집하는 것
    };

    //delete
    @Override
    public void delete(ID id){
        var deleteEntity = dataList.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst();

        if(deleteEntity.isPresent()){
            dataList.remove(deleteEntity.get());
        };
    };

    //이건 id와 관련된 공통기능이 아니기때문에 필요한 클래스 레포지토리에서 직접 정의해줘야함.
//    @Override
//    public List<T> findOverScore(int score){
//        var data = dataList.stream()
//                .filter(d -> d.getScore() >= score)
//                .collect(Collectors.toList());
//        System.out.println(data);
//        return data;
//
//    };

}
