package com.abnamor.task.mapper;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.abnamor.model.FutureTransaction;


public class TextLineMapperTest {

	
	private TextLineMapper textLineMapper;
	
	private String line;
	
	@BeforeEach
	public void setUp() throws Exception {
		
		InputColumns inputColumns =  new InputColumns("input/input_columns.txt", ",");		
		textLineMapper = new TextLineMapper();		
		ReflectionTestUtils.setField(textLineMapper, "inputColumns", inputColumns );
		
		line = "315CL  432100020001SGXDC FUSGX NK    20100910JPY01B 0000000001 0000000000000000000060DUSD000000000030DUSD000000000000DJPY201008200012380     688032000092500000000             O";
	}
 	
	@AfterEach
	public void tearDown() throws Exception {
		textLineMapper = null;
		line = null;
	}

	@Test
	public void testLineMapper() throws Exception {
		FutureTransaction futureTransaction = textLineMapper.map(line);
		assertNotNull(futureTransaction);
		assertEquals("CL", futureTransaction.getFutureTransactionKey().getClientType());
		assertEquals("4321", futureTransaction.getFutureTransactionKey().getClientNumber());
		assertEquals("0002", futureTransaction.getFutureTransactionKey().getAccountNumber());
		assertEquals("0001", futureTransaction.getFutureTransactionKey().getSubAccountNumber());
		
		assertEquals("SGX", futureTransaction.getFutureTransactionKey().getExchangeCode());
		assertEquals("20100910", futureTransaction.getFutureTransactionKey().getExpirationDate());
		assertEquals("FU", futureTransaction.getFutureTransactionKey().getProductGroupCode());
		assertEquals("NK", futureTransaction.getFutureTransactionKey().getSymbol());
		
		assertEquals(1L, futureTransaction.getQuantityLong());
		assertEquals(0L, futureTransaction.getQuantityShort());
		
	}
	
	@Test
	public void testLineMapperFailure() throws Exception {
	
		FutureTransaction futureTransaction = textLineMapper.map("318CL  432100020001SGXDC FUSGX NK    20100910JPY01B 0000000003 0000000000000000000060DUSD0");
		assertNotNull(futureTransaction);		
		assertNotEquals(1L, futureTransaction.getQuantityLong());
 
		
	}

}
