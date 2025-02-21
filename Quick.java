import java.util.Random;
public class Quick {
  public static int quickSelect(int[] data, int k, int start, int end){
    int pivot = partition(data, start, end);
    if(pivot > (k - 1)){
      return(quickSelect(data, k, start, pivot));
    }else if(pivot < (k - 1)){
      return(quickSelect(data, k, pivot + 1, end));
    }else{
      return(data[k - 1]);
    }
  }
  public static void quickSort(int[] data){
    quickSort(data, 0, data.length - 1);
  }
  public static void quickSort(int[] data, int start, int end) {
    if(end - start < 1){return;}
      int pivot = partition(data, start, end);
      quickSort(data, start, pivot - 1);
      quickSort(data, pivot + 1, end);
  }
  
  public static int partition( int [] data, int start, int end){
    int storeStart = start;
        int storeEnd = end;
		Random rand = new Random();
        int ind = -1;
        ind = rand.nextInt(end - start);
        ind = ind + start;
        final int store = data[ind];
        data[ind] = data[start];
        data[start] = store;
		while(start != end){
			if(data[start] < store){
				start++;
			}else if(data[start] > store){
				int temp = data[start];
				data[start] = data[end];
				data[end] = temp;
				end--;
			}else if(data[start] == store){
                start++;
            }
		}
        if(data[start] > data[storeStart]){start--;}
        moveStuff(data, start, storeStart);
	return(start);
  }


  public static void moveStuff(int[] data, int setPoint, int start){
    int store = data[start];
    int[] data1 = data.clone();
    for(int x = 0; x < start; x++){
      data[x] = data1[x];
    }
    for(int x = start; x < setPoint; x++){
      data[x] = data1[x + 1];
    }
    data[setPoint] = store;
    for(int x = setPoint + 1; x < data.length; x++){
      data[x] = data1[x];
    }
}
}
