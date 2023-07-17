package org.br.mineradora.service;

import org.br.mineradora.dto.OpportunityDTO;
import org.br.mineradora.dto.ProposalDTO;
import org.br.mineradora.dto.QuotationDTO;
import org.br.mineradora.entity.OpportunityEntity;
import org.br.mineradora.entity.QuotationEntity;
import org.br.mineradora.repository.OpportunityRepository;
import org.br.mineradora.repository.QuotationRepository;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class OpportunityServiceImpl implements OpportunityService {

    @Inject
    QuotationRepository quotationRepository;

    @Inject
    OpportunityRepository opportunityRepository;

    @Override //recupera informações de cotações, combina com a proposta e cria uma
    // oportunidade
    public void buildOpportunity(ProposalDTO proposalDTO) {
        List<QuotationEntity> quotationEntities = quotationRepository.findAll().list();
        //necessário para ordenar a lista ao contrário e fazer com que a última cotação
        // seja a primeira da lista.
        Collections.reverse(quotationEntities);

        OpportunityEntity opportunity = new OpportunityEntity();
        opportunity.setDate(new Date());
        opportunity.setProposalId(proposalDTO.getProposalId());
        opportunity.setCustomer(proposalDTO.getCustomer());
        opportunity.setPriceTonne(proposalDTO.getPriceTonne());
        opportunity.setLastDollarQuotation(quotationEntities.get(0).getCurrencyPrice());

        opportunityRepository.persist(opportunity);
    }

    @Override
    public void saveQuotation(QuotationDTO quotationDTO) {

    }

    @Override
    public List<OpportunityDTO> generateOpportunityData() {
        return null;
    }
}
