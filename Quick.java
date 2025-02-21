import java.util.Random;//This class is taken from my APCS repository, but I don't remember enough about it to write comments. 
public class Quick { // It implemenets the array sorting method quick sort and I have adapted it to also alter another array in the same way it alters the array which it is sorting.
  public static int quickSelect(int[] data, double[][] changeThis, int k, int start, int end){
    int pivot = partition(data, changeThis, start, end);
    if(pivot > (k - 1)){
      return(quickSelect(data, changeThis, k, start, pivot));
    }else if(pivot < (k - 1)){
      return(quickSelect(data, changeThis, k, pivot + 1, end));
    }else{
      return(data[k - 1]);
    }
  }
  public static void quickSort(int[] data, double[][] changeThis){
    quickSort(data, changeThis, 0, data.length - 1);
  }
  public static void quickSort(int[] data, double[][] changeThis, int start, int end) {
    if(end - start < 1){return;}
      int pivot = partition(data, changeThis, start, end);
      quickSort(data, changeThis, start, pivot - 1);
      quickSort(data, changeThis, pivot + 1, end);
  }
  
  public static int partition( int[] data, double[][] changeThis, int start, int end){
    int storeStart = start;
        int storeEnd = end;
		Random rand = new Random();
        int ind = -1;
        ind = rand.nextInt(end - start);
        ind = ind + start;
        final int store = data[ind];
        final double[] changeStore = changeThis[ind];
        data[ind] = data[start];
        data[start] = store;
        changeThis[ind] = changeThis[start];
        changeThis[start] = changeStore;
		while(start != end){
			if(data[start] < store){
				start++;
			}else if(data[start] > store){
				int temp = data[start];
        double[] changeTemp = changeThis[start];
				data[start] = data[end];
        changeThis[start] = changeThis[end];
				data[end] = temp;
        changeThis[end] = changeTemp;
				end--;
			}else if(data[start] == store){
                start++;
            }
		}
        if(data[start] > data[storeStart]){start--;}
        moveStuff(data, changeThis, start, storeStart);
	return(start);
  }


  public static void moveStuff(int[] data, double[][] changeThis, int setPoint, int start){
    int store = data[start];
    double[] changeStore = changeThis[start];
    int[] data1 = data.clone();
    double[][] change1 = java.util.Arrays.stream(changeThis).map(el -> el.clone()).toArray($ -> changeThis.clone()); //DeepClone one liner
    for(int x = 0; x < start; x++){
      data[x] = data1[x];
      changeThis[x] = change1[x];
    }
    for(int x = start; x < setPoint; x++){
      data[x] = data1[x + 1];
      changeThis[x] = change1[x + 1];
    }
    data[setPoint] = store;
    changeThis[setPoint] = changeStore;
    for(int x = setPoint + 1; x < data.length; x++){
      data[x] = data1[x];
      changeThis[x] = change1[x];
    }
}
}
