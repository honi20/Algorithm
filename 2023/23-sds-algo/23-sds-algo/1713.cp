#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
#include <string>
using namespace std;
typedef long long ll;
typedef pair<int, int> pii;
#define MAX 10002
#define INF 987654321

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n, m, x;
    vector <pair<int,int>> result;      // 번호, 시간
    int arr[102] = {0,};        // 추천 횟수
    
    cin>>n>>m;
    for (int i=0; i<m; i++){
        cin>>x;
        
        bool check = false;
        for (int j=0; j<result.size(); j++){
            if (result[j].first == x){
                check = true;
                break;
            }
        }
        
        if (check){
            arr[x]++;
        }
        
        else{
            if (result.size() < n){
                arr[x]++;
                result.push_back({x, i});
            }
            
            else {
                int minnum = 0, minidx = 0, minval = 1010, mintime = 1010;
                
                for (int j=0; j<result.size(); j++){
                    if (arr[result[j].first] < minval || (arr[result[j].first] == minval && result[j].second < mintime)){
                        minnum = result[j].first;
                        minidx = j;
                        minval = arr[result[j].first];
                        mintime = result[j].second;
                    }
                }
                arr[minnum] = 0;
                result.erase(result.begin() + minidx);
                arr[x]++;
                result.push_back({x, i});
            }
        }
    }
    sort(result.begin(), result.end());
    for (int i=0; i<result.size(); i++)
        cout<<result[i].first<<" ";
}

/*
 1. 해당 번호가 후보 안에 있는 경우
    해당 번호 추천횟수 증가
 
 2. 없는 경우
    2-1. 사진틀이 비어있는 경우
        추천 횟수 증가 -> 후보 안에 추가
    2-2. 사진틀이 차있는 경우
        후보들 중 추천 횟수 작은 것 선택 -> 가장 오래된 것 선택 -> 삭제 & 추천횟수 초기화 -> 추천횟수 증가 후 추가
 */




/*
 추천 받으면 무조건 게시
 사진틀 안비어있으면
    가장 적은 학생 사진 삭제
    2명 이상일 경우 오래된 사진 삭제
 추천받은 횟수만 증가시킴
 삭제 시 추천받은 횟수 0
*/
