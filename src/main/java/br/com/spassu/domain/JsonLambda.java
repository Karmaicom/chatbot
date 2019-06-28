package br.com.spassu.domain;

public class JsonLambda {

	private String responseId;
	private QueryResult queryreult;
	private OriginalDetectIntentRequest originalDetectIntentRequest;

	public String getResponseId() {
		return responseId;
	}

	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}

	public QueryResult getQueryreult() {
		return queryreult;
	}

	public void setQueryreult(QueryResult queryreult) {
		this.queryreult = queryreult;
	}

	public OriginalDetectIntentRequest getOriginalDetectIntentRequest() {
		return originalDetectIntentRequest;
	}

	public void setOriginalDetectIntentRequest(OriginalDetectIntentRequest originalDetectIntentRequest) {
		this.originalDetectIntentRequest = originalDetectIntentRequest;
	}

	/*
	 * 
	 * {"responseId": "b9de7dd5-d645-44f5-bec7-f84b690a1549-64cfa233",
	 * "queryResult": {"action": "acionar"}, "originalDetectIntentRequest":
	 * {"payload": {"data": {"message": { "from":{"username": "rararipe", "id":
	 * 800924953.0}, "chat": {"id": 800924953.0, "username": "rararipe"}, "text":
	 * "O que são placas petrográficas?" } } } } }
	 * 
	 */

}
