package td3;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class VariableSymboliqueMapping {

	Map<VariableSymbolique, ExpressionArithmetique> mapVariableSymbolique = new HashMap<>();

	public VariableSymboliqueMapping(VariableSymbolique variableSymbolique, ExpressionArithmetique ea) {
		this.mapVariableSymbolique.put(variableSymbolique, ea);
	}

	public VariableSymboliqueMapping() {

	}

	public VariableSymboliqueMapping(List<VariableSymbolique> listeVariableSymbolique,
			List<ExpressionArithmetique> listeEa) {

		Iterator itListVS = listeVariableSymbolique.iterator();
		Iterator itListEA = listeEa.iterator();

		while (itListVS.hasNext() && itListEA.hasNext()) {

			mapVariableSymbolique.put((VariableSymbolique) itListVS.next(), (ExpressionArithmetique) itListEA.next());
		}

	}

	public Map getMapVariableSymbolique() {
		return this.mapVariableSymbolique;
	}

	public String toString() {
		StringBuilder mapAsString = new StringBuilder("{");
		for (VariableSymbolique key : this.mapVariableSymbolique.keySet()) {
			mapAsString.append(key + "=" + this.mapVariableSymbolique.get(key) + ", ");
		}
		mapAsString.delete(mapAsString.length() - 2, mapAsString.length()).append("}");
		return mapAsString.toString();
	}

}
