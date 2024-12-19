

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * 16:25
 *
 * 문제 설명
 *  ~~ 1초마다
 * 1. 낚시왕이 오른쪽 한칸으로 이동
 * 2. 낚시왕 열 기준 가장 가까운 상어를 잡음 -> 사라짐
 * 3. 상어 이동
 *
 * 필요한 메서드
 * 1. 가장 가까운 상어를 찾는 메서드 - findShark
 * 2. 상어가 이동하는 메서드
 * 3. 위치 같은 상어 크기 큰 상어가 작은 상어 잡아먹는 메서드
 *
 * 필요한 변수
 * Shark[] sharks
 * int eat
 */
public class Main {

    public static class Shark implements Comparable<Shark>{
        int r; // x좌표
        int c; // y좌표
        int s; // 속력
        int d; // 이동방향
        int z; // 크기
        Character number;

        public Shark(int r, int c, int s, int d, int z, Character number) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
            this.number = number;
        }

        public int getR() {
            return r;
        }

        public void setR(int r) {
            this.r = r;
        }

        public int getC() {
            return c;
        }

        public void setC(int c) {
            this.c = c;
        }

        public int getS() {
            return s;
        }

        public void setS(int s) {
            this.s = s;
        }

        public int getD() {
            return d;
        }

        public void setD(int d) {
            this.d = d;
        }

        public int getZ() {
            return z;
        }

        public void setZ(int z) {
            this.z = z;
        }

        public Character getNumber() {
            return number;
        }

        public void setNumber(Character number) {
            this.number = number;
        }

        @Override
        public String toString() {
            return "Shark{\n" +
                    "번호=" + number + "\n"+
                    ", r=" + (r+1) + "\n"+
                    ", c=" + (c+1) + "\n"+
                    ", s=" + s + "\n"+
                    ", d=" + d + "\n"+
                    ", z=" + z + "\n"+
                    '}';
        }

        @Override
        public int compareTo(Shark o) {
            return this.z-o.z; // 오름차순
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()); // 격자판 가로 크기
        C = Integer.parseInt(st.nextToken()); // 격자판 세로 크기
        int M = Integer.parseInt(st.nextToken()); // 상어 갯수
        List<Shark> sharkList = new ArrayList<>();
        char num = 'A';
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            if(d==1 | d==2){
                s = s%((R-1)*2);
            }
            else{
                s = s%((C-1)*2);
            }
            Shark shark = new Shark(r,c,s,d,z,num++);
            sharkList.add(shark);
        }
        eat = 0;
        int king = -1;
//        System.out.println("---초기 상어---");
//        System.out.println(sharkList);
        while (king<C-1){
//          1. 낚시왕이 오른쪽 한칸으로 이동
            king++;
//            System.out.println("낚시왕 위치: "+(king+1));
//          2. 낚시왕 열 기준 가장 가까운 상어를 잡음 -> 사라짐
            findShark(king, sharkList);
//            System.out.println("먹은 상어: "+eat);
//          3. 상어 이동
            moveShark(sharkList);

        }
        System.out.println(eat);

    }
    static int R;
    static int C;

    private static void moveShark(List<Shark> sharkList) {
        // 상어들 이동
        for (int i = 0; i < sharkList.size(); i++) {
            Shark shark = sharkList.get(i);
            int r = shark.getR();
            int c = shark.getC();
            int s = shark.getS(); // 속력
            int d = shark.getD(); // 이동방향

            if (d == 1 || d == 2) {
                int range = (R - 1) * 2; // 한쪽 끝에서 다른 끝을 왕복하는 거리
                int pos;
                if (d == 1) { // 위로 이동
                    pos = (r - s + range) % range; // 이동 후 위치 계산 (음수 방지)
                } else { // 아래로 이동
                    pos = (r + s) % range; // 이동 후 위치 계산
                }

                if (pos >= R) { // 격자를 벗어났을 경우 반대 방향으로 이동
                    pos = range - pos;
                    if (d == 1) {
                        shark.d = 2; // 위에서 아래로 전환
                    } else {
                        shark.d = 1; // 아래에서 위로 전환
                    }
                }

                shark.r = pos; // 새로운 행 위치 저장
            }

            else {
                int range = (C - 1) * 2; // 한쪽 끝에서 다른 끝을 왕복하는 거리
                int pos;
                if (d == 4) { // 왼쪽으로 이동
                    pos = (c - s + range) % range; // 이동 후 위치 계산 (음수 방지)
                } else { // 오른쪽으로 이동
                    pos = (c + s) % range; // 이동 후 위치 계산
                }

                if (pos >= C) { // 격자를 벗어났을 경우 반대 방향으로 이동
                    pos = range - pos;
                    if (d == 4) {
                        shark.d = 3; // 왼쪽에서 오른쪽으로 전환
                    } else {
                        shark.d = 4; // 오른쪽에서 왼쪽으로 전환
                    }
                }

                shark.c = pos; // 새로운 열 위치 저장
            }
        }

//        System.out.println("---상어 이동!---");
//        System.out.println(sharkList);
        // 위치가 동일한 경우 큰 상어가 작은 상어 잡아먹기
        Map<String, Shark> positionMap = new HashMap<>();

        for (Shark shark : sharkList) {
            String position = shark.getR() + "," + shark.getC(); // 상어 위치를 키로 사용
            if (positionMap.containsKey(position)) {
                Shark existingShark = positionMap.get(position);
                if (shark.getZ() > existingShark.getZ()) { // 크기가 더 큰 상어로 갱신
                    positionMap.put(position, shark);
                }
            } else {
                positionMap.put(position, shark); // 새로운 위치에 상어 추가
            }
        }

// Map에 남아있는 상어들만 유지
        sharkList.clear();
        sharkList.addAll(positionMap.values());


    }

    static int eat;

    private static void findShark(int king, List<Shark> sharkList) {

        Shark closeShark = null;
        for(Shark shark: sharkList){
            if(shark.getC()==king){
                if(closeShark == null || shark.getR() < closeShark.getR()){
                    closeShark = shark;
                }
            }
        }

        if(closeShark != null){
            eat += closeShark.getZ();
            sharkList.remove(closeShark);
        }

//        int distance = Integer.MAX_VALUE;
//        int idx = -1;
//        for(int i =0; i<sharkList.size();i++){
//            if(sharkList.get(i).getC()==king){
//                if(distance>sharkList.get(i).getR()){
//                    idx = i;
//                    distance = sharkList.get(i).getR();
//                }
//            }
//        }

//        if(idx == -1){ // 같은 열에 상어가 없으면 넘어감
//            return;
//        }
//        else{
////            System.out.println(sharkList.get(idx).getNumber()+"번째 상어 잡아먹음");
//            eat+=sharkList.get(idx).getZ();
//            sharkList.remove(idx);
//            return;
//        }

    }

}
