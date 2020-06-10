package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.FulfillmentRequest;
import pojo.Key;
import pojo.Location;
import pojo.Value;

public class TestDataBuild {

	public AddPlace addPlacePayLoad() {
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress("29, side layout, cohen 09");
		ap.setLanguage("French-IN");
		ap.setName("Frontline house");
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setWebsite("http://google.com");

		List<String> list = new ArrayList<String>();
		list.add("shoe park");
		list.add("shop");
		ap.setTypes(list);

		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		ap.setLocation(loc);
		return ap;
	}

	public FulfillmentRequest fulfillmentRequestPayload() {
		FulfillmentRequest ftpayload = new FulfillmentRequest();
		Key k = new Key();
		Value val = new Value();
		String ft_id = "673490001";

		k.setId(ft_id);
		val.setAddressType("1");
		val.setApplicationIdentifier("");
		val.setAsOfDate("2020-04-22 00:00:00");
		val.setAuxiliaryPersonIdentifier("7531909");
		val.setCarrier("");
		val.setEmployerIdentifier("");
		val.setFulfillmentTriggerTypeId("10054");
		val.setHouseholdIdentifier("33884700");
		val.setIndividualIdentifier("9800033864198");
		val.setReadFromFile("Yes");
		val.setReferenceId(ft_id);
		val.setShippingMethod("");
		val.setSourceSystem("GPS");
		val.setSubmitDate("2020-04-22 05:23:57");
		val.setIndividualIdentifier("");

		ftpayload.setKey(k);
		ftpayload.setValue(val);

		return ftpayload;

	}
	
	public String getFtDetailsPayload(String id) {
		return "";
	}

}
