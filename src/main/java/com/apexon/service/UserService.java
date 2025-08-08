//package com.apexon.service;
//
//import com.apexon.entity.User;
//import com.apexon.repository.UserRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.StringUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
///**
// * Service class that provides business logic for managing users.
// * This class interacts with the database through {@link userRepository} and
// */
//@Service
//@Slf4j
//public class UserService {
//
//    protected static final String logPrefix = "{} : {}";
//
//    @Autowired
//    private UserRepository userRepository;
//
//
//    @Override
//    public User create(Organization organization, Identifier identifier, Boolean isVerified) {
//        return null;
//    }
//
//    /**
//     * Creates a new organization with the given request and identifier.
//     *
//     * @param organizationRequest the organization request object containing organization details
//     * @param identifier     headers
//     * @param isVerified          verification flag
//     * @return the created {@link Organization} entity
//     */
//    @Transactional
//    public User create(OrganizationRequest organizationRequest, Identifier identifier, Boolean isVerified) {
//        log.info(logPrefix, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
//        Organization organization = buildOrganization(new Organization(), organizationRequest, identifier, true);
//        rolesAndPermissionHelper.createDefaultRolesAndPermission(organization,identifier, ResourceType.ORGANIZATION);
//        return organizationDao.create(organization, identifier);
//    }
//
//    @Override
//    public Organization update(Organization organization, Identifier identifier, Boolean isVerified) {
//        return null;
//    }
//
//    /**
//     * Updates an existing organization with the provided request and identifier.
//     *
//     * @param organizationRequest the organization request object containing updated details
//     * @param identifier  UUID - OrganizationId
//     *                    headers
//     * @param isVerified          verification flag
//     * @return the updated {@link Organization} entity
//     */
//    @Transactional
//    public User update(OrganizationRequest organizationRequest, Identifier identifier, Boolean isVerified) {
//        log.info(logPrefix, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
//        Organization organization = get(identifier, isVerified);
//        buildOrganization(organization, organizationRequest, identifier, false);
//        organization = organizationDao.update(organization, identifier);
//
//        OrganizationUpdateRequest organizationUpdateRequest = new OrganizationUpdateRequest(organization.getName());
//        OrganizationUpdateResponse organizationUpdateResponse = organisationHelper.updateOrganization(cmUtil.writeValueAsString(organizationUpdateRequest), identifier);
//        return organization;
//    }
//
//    /**
//     * Retrieves an organization by its identifier.
//     *
//     * @param identifier  UUID - OrganizationId
//     * @param isVerified  verification flag
//     * @return the retrieved {@link Organization} entity
//     * @throws CaseManagerException if the organization is not found
//     */
//    @Override
//    public Organization get(Identifier identifier, Boolean isVerified) {
//        log.info(logPrefix, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
//        return organizationDao.getV1(identifier).orElseThrow(() -> new CaseManagerException(ErrorFactory.RESOURCE_NOT_FOUND));
//    }
//
//    @Override
//    public Organization getForList(Identifier identifier, Boolean isVerified) {
//        return null;
//    }
//
//    @Override
//    public Organization forList(Organization organization, Identifier identifier, Boolean isVerified) {
//        return null;
//    }
//
//    /**
//     * Lists all organizations for the provided identifier.
//     *
//     * @param identifier word- querytext
//     *                  headers
//     * @param isVerified verification flag
//     * @return a list of {@link Organization} entities
//     */
//    @Override
//    public List<User> list(Identifier identifier, Boolean isVerified) {
//        log.info(logPrefix, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
//        List<User> organizations = userRepository.findAll();
//        return organizations;
//    }
//
//    /**
//     * Deletes an organization by its identifier.
//     *
//     * @param identifier  UUID- OrganizationId
//     * @param isVerified  verification flag
//     * @return true if the organization was deleted successfully, false otherwise
//     */
//    @Override
//    @Transactional
//    public boolean delete(Identifier identifier, Boolean isVerified) {
//        log.info(logPrefix, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
//        User user = get(identifier, isVerified);
//        return userRepository.delete(organization) > 0;
//    }
//
//    /**
//     * Helper method to build an {@link Organization} entity from a request.
//     * @param identifier UUID - OrganizationId
//     *                  headers
//     * @param organization         the organization entity to populate
//     * @param organizationRequest  the organization request object
//     * @param isNew                flag to indicate if the organization is new
//     * @return the populated {@link Organization} entity
//     */
//    private User buildOrganization(Organization organization, OrganizationRequest organizationRequest, Identifier identifier, boolean isNew) {
//        organization.setName(organizationRequest.getName());
//        organization.setDescription(organizationRequest.getDescription());
//
//        if (isNew) {
//            organization.setOrganizationTypeCd(organizationRequest.getOrganizationTypeCd());
//            organization.setOrganizationId(organizationRequest.getOrganizationId());
//            organization.setCreatedBy(TokenHelper.getUserId(identifier));
//        } else {
//            organization.setUpdatedAt(getUnixTimestampInUTC());
//        }
//        organization.setUpdatedBy(TokenHelper.getUserId(identifier));
//        return organization;
//    }
//}
