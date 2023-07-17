package org.br.mineradora.message;

import io.smallrye.common.annotation.Blocking;
import org.br.mineradora.dto.ProposalDTO;
import org.br.mineradora.dto.QuotationDTO;
import org.br.mineradora.service.OpportunityService;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.transaction.Transactional;

public class KafkaEvent {

    private final Logger LOG = LoggerFactory.getLogger(KafkaEvent.class);

    @Inject
    OpportunityService opportunityService;

    @Incoming("proposal") //Indica que vamos receber dados do tópico proposal lá do kafka.
    @Transactional
    public void receiveProposal(ProposalDTO proposalDTO) {
        LOG.info("--- Recebendo Nova Proposta do Tópico Kafka ---");
        opportunityService.buildOpportunity(proposalDTO);
    }

    @Incoming("proposal")
    @Blocking
    public void receiveQuotation(QuotationDTO quotationDTO) {
        LOG.info("--- Recebendo Nova Cotação de Moeda do Tópico Kafka ---");
        opportunityService.saveQuotation(quotationDTO);
    }



}
