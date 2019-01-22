public class ExpressionEvaluator extends Stack{
}

class Node{
	public double data;
	public char op;
	public Node prev;
};

class Stack
{
	private Node top;
	private int count;

	public	Stack(){
		top = null;
		count = 0;
	}
		
	public	Stack reverse(){
		Stack reverse = new Stack();
		Node temp = this.top;
		while(temp != null){
			reverse.push(temp.data,temp.op);
			temp = temp.prev;
		}
		return reverse;
	}
	public	boolean isEmpty(){
		return top == null;
	}
	public  void push(double val){
		Node n = new Node();
		n.data = val;
		n.prev = top;
		n.op = ' ';
		top = n;
		count++;
	}
	public	void push(double val,char op ){
		push(val);
		top.op = op;
	}
	public	void pop(){
		if(!isEmpty()){
			Node temp  = top;
			top = top.prev;
			count--;
		}
	}
	public	double  peek(){
		return top.data;
	}
	public	int  size(){
		return count;
	}
	public	static double  evaluate(String data){
		Stack obj = new Stack();
		data += "+0";
		int loop = data.length();
		int startIndex = 0;
		double result = 0;
		for (int i = 0; i < loop; i++)
		{
			char temp = data.charAt(i);
			if (!Character.isDigit(temp) && !(temp=='.'))
			{
				obj.push(Double.valueOf(data.substring(startIndex,i)),temp);
				startIndex = i+1;
			}
		}
		obj.push(Double.valueOf(data.substring(startIndex,loop)));
		Stack tempStack = obj.reverse();

		Stack addSubMulDiv = new Stack();
		double n1,n2;
		char op;
		while(!tempStack.isEmpty()){
			
			while(tempStack.top.op == '/' || tempStack.top.op == '*'){
				char divOrMul = tempStack.top.op;
				n1 = tempStack.peek();
				tempStack.pop();
				n2 = tempStack.peek();
				op = tempStack.top.op;
				tempStack.pop();
				if(op == '/' || op == '*'){
					if(divOrMul == '/'){
						tempStack.push(n1/n2,op);
					}else{
						tempStack.push(n1*n2,op);
					}
				}else{
					if(divOrMul == '/'){
						addSubMulDiv.push(n1/n2,op);
					}else{
						addSubMulDiv.push(n1*n2,op);
					}	
				}
			}
			op = tempStack.top.op;
			addSubMulDiv.push(tempStack.peek(),op);
			tempStack.pop();
		}
		tempStack = addSubMulDiv.reverse();
		int size=tempStack.size();
		 for(int i=0;i<size;i++){
		 	char plusOrMinus = tempStack.top.op;
			if(plusOrMinus== '+' || plusOrMinus == '-'){
				n1 = tempStack.peek();
				tempStack.pop();
				n2 = tempStack.peek();
				op = tempStack.top.op;
				tempStack.pop();

				if(plusOrMinus == '+'){
					tempStack.push(n1+n2,op);
				}
				else{
					tempStack.push(n1-n2,op);
				}
			}
		}
		result = tempStack.peek();

		return result;
	}

};
